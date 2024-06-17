import 'dart:convert';

import 'package:craftmentor_backend/models/player.dart';
import 'package:postgres/postgres.dart';
import 'package:shelf/shelf.dart';
import 'package:shelf_router/shelf_router.dart';

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

  app.get('/player-exists/<name>', (Request request) async {
    final query = request.params;
    final name = query['name'];

    try {
      final result = await sql.execute(
        Sql.named('''SELECT * FROM "PLAYER" WHERE name=@name;'''),
        parameters: {'name': name},
      );
      final data = result
          .map((row) => row.map((e) => e).toList())
          .map(
            (player) => Player.fromObject(
              player,
            ),
          )
          .toList();
      return Response.ok(jsonEncode(data.firstOrNull?.toMap));
    } catch (e) {
      return Response.badRequest(body: jsonEncode(null));
    }
  });

  app.post('/players', (Request request) async {
    try {
      final body = await request.readAsString();
      final bodyParams = jsonDecode(body) as Map<String, dynamic>;
      final id = bodyParams['id'];
      final name = bodyParams['name'];

      final insertionParams = {
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
        parameters: insertionParams,
      );

      return Response.ok(jsonEncode(insertionParams));
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
      final body = await request.readAsString();
      final query = request.params;
      final bodyParams = jsonDecode(body) as Map<String, dynamic>;
      final name = query['name'];
      final points = bodyParams['points'];

      final updateParams = {
        'name': name,
        'points': points,
      };

      await sql.execute(
        Sql.named(
          '''
UPDATE "PLAYER" SET points=@points WHERE name=@name;
''',
        ),
        parameters: updateParams,
      );

      return Response.ok(jsonEncode(updateParams));
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
