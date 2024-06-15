class Player {
  final String id;
  final String name;
  final double points;

  Player({
    required this.id,
    required this.name,
    required this.points,
  });

  factory Player.fromObject(dynamic obj) {
    final data = {
      'id': obj[0],
      'name': obj[1],
      'points': obj[2],
    };

    return Player(
      id: data['id'],
      name: data['name'],
      points: data['points'],
    );
  }

  Map<String, dynamic> get toMap => {
        'id': id,
        'name': name,
        'points': points,
      };
}
