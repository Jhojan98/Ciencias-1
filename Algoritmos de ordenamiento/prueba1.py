import pickle
"""
with open('100.pkl', 'rb') as file:
    data = pickle.load(file)

with open('10.pkl', 'rb') as file:
    data1 = pickle.load(file)
with open('1000.pkl', 'rb') as file:
    data2 = pickle.load(file)
with open('10000.pkl', 'rb') as file:
    data3 = pickle.load(file)
"""
with open('1000.pkl', 'rb') as file:
    list = pickle.load(file)
print(list)
cicles = 0
steps = 0

def sort(list, pos0, pivotPos):
    global cicles, steps
    pivot = list[pivotPos]
    j = pivotPos-1
    swaped = False

    for i in range(pos0, pivotPos):
        cicles += 1
        if list[i] > pivot:
            steps += 1
            swaped = True
            while i <= j:
                cicles += 1
                if list[j] < pivot:
                    steps += 1
                    list[i], list[j] = list[j], list[i]
                    j -= 1
                    break
                j -= 1

        if i > j or not swaped and i == pivotPos-1:
            steps += 1
            if swaped: 
                steps += 1
                list[i], list[pivotPos] = pivot, list[i]
            else:
                steps += 1
                i = pivotPos

            if (i-1) > pos0:
                steps += 1
                sort(list, pos0, i-1)
            if (i+1) < pivotPos:
                steps += 1
                sort(list, i+1, pivotPos)

            return list

print(sort(list, 0, len(list)-1))
print('cicles:', cicles)
print('steps:', steps)
