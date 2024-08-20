import pickle
with open('100.pkl', 'rb') as file:
    data = pickle.load(file)
print(data)

ciclos = 0
pasos= 0

def quickSort(list, start_index, end_index):
    global ciclos, pasos
    pivot = list[end_index]
    j = end_index-1
    swaped = False

    for i in range(start_index, end_index):
        ciclos += 1
        if data[i] > pivot:
            pasos += 1
            swaped = True
            while i <= j:
                ciclos += 1
                if data[j] < pivot:
                    pasos += 1
                    data[i], data[j] = data[j], data[i]
                    j -= 1
                    break
                j -= 1

        if i > j or not swaped and i == end_index-1:
            pasos += 1
            if swaped: 
                pasos += 1
                data[i], data[end_index] = pivot, data[i]
            else:
                pasos += 1
                i = end_index

            if (i-1) > start_index:
                pasos += 1
                quickSort(data, start_index, i-1)
            if (i+1) < end_index:
                pasos += 1
                quickSort(data, i+1, end_index)

            return data

sorted_data = quickSort(data, 0, len(data) - 1)
print(sorted_data)
print('Total ciclos:', ciclos)
print('Total pasos:', pasos)

