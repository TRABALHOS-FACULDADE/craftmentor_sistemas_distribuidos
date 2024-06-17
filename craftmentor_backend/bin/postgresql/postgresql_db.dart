import 'package:postgres/postgres.dart';

class PostgreSQLDB {
  static Future<Connection> startConnection() async {
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

    return conn;
  }
}
