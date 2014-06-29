#!/usr/bin/python2.7

import matplotlib.pyplot as plt
import numpy as np

def readData():
	# Read the values of x and y from a text file
	x = []
	y = []
	with open('SampleData.txt') as f:
		next(f)
		for line in f:
			data = line.split()
			first = float(data[0])
			second = float(data[1])

			# Skip data entries with 0.0 because they mess
			# up our calculations #goodscientist
			if first == 0.0 or second == 0.0:
				continue

			x.append(first)
			y.append(second)
	return (x, y)

def lineFit(x, y):
    # Returns slope and y-intercept of linear fit to (x,y) data set
	xavg = x.mean()
	slope = (y*(x-xavg)).sum()/(x*(x-xavg)).sum()
	yint = y.mean()-slope*xavg
	return slope, yint

def toLog_2(x):
	# Return the log (base 2) of x and y
	return np.log2(x)




read_x, read_y = readData()

x = np.array(read_x)
y = np.array(read_y)

log_x = toLog_2(x)
log_y = toLog_2(y)

print(log_x)
print(log_y)
print("")
print("")

slope, yint = lineFit(log_x, log_y)

print("Slope: " + str(slope))
print("Y-intercept: " + str(yint))
print("")

plt.plot(log_x, log_y)
plt.show()












