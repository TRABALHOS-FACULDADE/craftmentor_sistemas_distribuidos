import 'postgresql/postgresql_db.dart';
import 'package:shelf/shelf_io.dart' as shelf_io;
import 'package:shelf_router/shelf_router.dart';
import 'requests/players_requests.dart';

Future<void> main(List<String> arguments) async {
  // Oppening connection with PostgreSQL
  final conn = await PostgreSQLDB.startConnection();

  // Starting Shelf-Router handler
  var app = Router();

  // [PlayerRequests] is a class which starts all Player requests methods
  PlayerRequests(app: app, sql: conn);

  // Starting Shelf-Router Dart Server at address: 0.0.0.0:8001
  var server = await shelf_io.serve(app.call, '0.0.0.0', 8001);
  print('Dart server running in ${server.address.host}:${server.port}');
  server.autoCompress = true;
}
