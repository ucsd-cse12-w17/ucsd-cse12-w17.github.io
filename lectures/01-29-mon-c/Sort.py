
def is_sorted(array):
  i = 0
  while i < len(array) - 1:
    if array[i] > array[i + 1]:
      return False
    i += 1
  return True




    
#  for i in range(0, len(array) - 1):
#    if array[i] > array[i + 1]:
#      return False

























import time
import sys


size = int(sys.argv[1])

array = range(0, size * 10, 10)
start = time.time() * 1000
print is_sorted(array)
end = time.time() * 1000
print end - start

