'''
Implementation of ShellSort
As seen on Wikipedia, the free encyclopedia
http://en.wikibooks.org/wiki/Algorithm_Implementation/Sorting/Shell_sort

Modified gap sequence to 3x + 1 (faster than len / 2)
according to https://class.coursera.org/algs4partI-005/lecture/27
'''
def shellSort(array):
	gap = 1
	while( gap < len(array) // 3):
		gap = 3*gap + 1

	# loop over the gaps
	while gap >= 1:
		# do the insertion sort
		for i in range(gap, len(array)):
			val = array[i]
			j = i
			while j >= gap and array[j - gap] > val:
				array[j] = array[j - gap]
				j -= gap
			array[j] = val
		gap //= 3

	return array



# array = [2,1,4,-1,300,0]
# print(shellSort(array))