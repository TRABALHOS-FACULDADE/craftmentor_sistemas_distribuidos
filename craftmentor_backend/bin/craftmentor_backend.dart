import 'package:postgres/postgres.dart';
import 'package:shelf/shelf_io.dart' as shelf_io;
import 'package:shelf_router/shelf_router.dart';
import '../bin/functions/players_requests.dart';

Future<void> main(List<String> arguments) async {
  // Abre uma conexão com o PostgreSQL
  final conn = await Connection.open(
    Endpoint(
      host: 'localhost',
      database: 'dart',
      username: 'root',
      password: 'password',
      port: 5435,
    ),
    settings: ConnectionSettings(
      sslMode: SslMode.disable,
    ),
  );

  if (conn.isOpen) {
    print('Connected to Postgres!\n');
  }

  var app = Router();
  playerRequests(app: app, sql: conn);

  // Inicializa o servidor Dart com o Dart Shelf
  var server = await shelf_io.serve(app.call, '0.0.0.0', 8001);
  print('Dart server running in ${server.address.host}:${server.port}');
  server.autoCompress = true;
}
