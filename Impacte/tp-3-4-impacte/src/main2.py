import matplotlib.pyplot as plt
  
x = []
y = []
for line in open('data.txt', 'r'):
    lines = [i for i in line.split()]
    x.append(lines[0])
    y.append(int(lines[1]))
      
plt.title("Students Marks")
plt.xlabel('Roll Number')
plt.ylabel('Marks')
plt.yticks(y)
plt.loglog(x, y, marker = 'o', c = 'g')
  
plt.show()