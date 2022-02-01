import socket

HOST = socket.gethostname()
PORT = int(input('Port'))
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print('Connexion vers ' + HOST + ':' + str(PORT) + ' reussie.')
print('\'stop\' pour mettre fin à la connection ')

message='input'

while message != 'stop\n':
    message = input()+'\n'
    data1 = bytes(message, 'utf-8')
    s.send(data1)

s.close()
print('Deconnexion')
