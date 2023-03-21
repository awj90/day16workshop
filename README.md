# day16workshop
RESTful API that persists Mastermind Game data to a Redis data store
Sample JSON format of the game data:

{
    "name": "Mastermind",
    "pieces": {
        "decoding_board": {
            "total_count": 1
        },
        "pegs": {
            "total_count": 102,
            "types": [
                {
                    "type": "code",
                    "count": 72
                },
                {
                    "type": "key",
                    "count": 30
                }
            ]
        },
        "rulebook": {
            "total_count": 1,
            "file": "rulebook-ultimate-mastermind.pdf"
        }
    }
}
