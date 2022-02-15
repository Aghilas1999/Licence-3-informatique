import random
import sys
import numpy as np
import time

if __name__ =="__main__":
	MAX = 2*10**9
	n = int(sys.argv[1])
	#tableau = random.sample(range(-MAX,MAX),n)

	#start = time.time()
	#tableau.sort()
	#end = time.time()
	#elapsed = end - start
	#print(elapsed)
	
	tableau = np.random.randint(low=-MAX,high=MAX,size=n)
	start = time.time()
	np.sort(tableau)
	end = time.time()
	elapsed = end - start
	print(f'{elapsed:.20f}')
