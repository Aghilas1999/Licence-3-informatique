import math
import os
from re import A 
import sys
import csv


def tri_langage(lang : str, n : int  ):
    lang = lang.upper()
    if(lang == "JAVA"):
        return os.popen("javac quickSort.java && java quickSort "+ str(int(n)*i)).read()
    elif lang == "C":
        return os.popen("gcc -o quick_sort quick_sort.c  && ./quick_sort " + str(n) ).read()
    elif lang == "PYTHON":
        return os.popen("python3 python_sort.py " + str(n)).read()
if __name__ =="__main__":
    #16000000
    data = [{"java : "}]
    lang = input("{Choisisez entre ces 3 langages {java , python , c} } ")
    with open('data.txt', 'w', newline='') as file:
        writer = csv.writer(file)
        for i in range(1,10):
                writer.writerow([i*14000000, tri_langage(lang,i*14000000)])
                        

               