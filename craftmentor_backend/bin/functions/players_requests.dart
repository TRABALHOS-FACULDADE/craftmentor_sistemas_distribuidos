import 'dart:convert';

import 'package:craftmentor_backend/models/player.dart';
import 'package:postgres/postgres.dart';
import 'package:shelf/shelf.dart';
import 'package:shelf_router/shelf_router.dart';
import 'package:uuid/uuid.dart';

void playerRequests({
  required Router app,
  required Connection sql,
}) {
  app.get('/players', (Request request) async {
    try {
      final result = await sql.execute('SELECT * FROM "PLAYER";');
      final data = result
          .map((row) => row.map((e) => e).toList())
          .map(
            (player) => Player.fromObject(
              player,
            ),
          )
          .toList();
      return Response.ok(jsonEncode(data.map((e) => e.toMap).toList()));
    } catch (e) {
      return Response.badRequest(
        body: jsonEncode(
          {
            'error': 'Não foi possível listar os jogadores.',
            'serverMessage': e.toString(),
          },
        ),
      );
    }
  });

  app.post('/players', (Request request) async {
    try {
      final id = Uuid().v4();
      final query = await request.readAsString();
      final parameters = jsonDecode(query) as Map<String, dynamic>;
      final name = parameters['name'];

      final insertionParameters = {
        'id': id,
        'name': name,
        'points': 0,
      };

      await sql.execute(
        Sql.named(
          '''
INSERT INTO "PLAYER" (id, name, points) VALUES(@id, @name, @points);
''',
        ),
        parameters: insertionParameters,
      );

      return Response.ok(jsonEncode(insertionParameters));
    } catch (e) {
      return Response.badRequest(
        body: jsonEncode(
          {
            'error': 'Não foi possível inseir jogador.',
            'serverMessage': e.toString(),
          },
        ),
      );
    }
  });

  app.patch('/player/<name>', (Request request) async {
    try {
      final query = await request.readAsString();
      final parameters = jsonDecode(query) as Map<String, dynamic>;
      final name = request.params['name'];
      final points = parameters['points'];

      final updateParameters = {
        'name': name,
        'points': points,
      };

      await sql.execute(
        Sql.named(
          '''
UPDATE "PLAYER" SET points=@points WHERE name=@name;
''',
        ),
        parameters: updateParameters,
      );

      return Response.ok(jsonEncode(updateParameters));
    } catch (e) {
      return Response.badRequest(
        body: jsonEncode(
          {
            'error': 'Não foi possível atualizar pontuação do jogador jogador.',
            'serverMessage': e.toString(),
          },
        ),
      );
    }
  });
}
