import pickle
with open('100.pkl', 'rb') as file:
    data = pickle.load(file)


ciclos = 0
pasos= 0

def quicksort(array, start_index, end_index):
    global ciclos, total_swaps
    pivot_value = array[end_index]
    left_pointer = start_index - 1
    swapped = False

    for current_index in range(start_index, end_index):
        global ciclos, pasos
        ciclos += 1
        if array[current_index] > pivot_value:
            swapped = True
            while current_index <= left_pointer:
                ciclos += 1
                if array[left_pointer] < pivot_value:
                    pasos += 1
                    array[current_index], array[left_pointer] = array[left_pointer], array[current_index]
                    left_pointer -= 1
                    break
                left_pointer -= 1

        if current_index > left_pointer or not swapped and current_index == end_index - 1:
            if swapped:
                pasos+= 1
                array[current_index], array[end_index] = pivot_value, array[current_index]
            else:
                current_index = end_index

            if (current_index - 1) > start_index:
                quicksort(array, start_index, current_index - 1)
            if (current_index + 1) < end_index:
                quicksort(array, current_index + 1, end_index)

            return array

sorted_data = quicksort(data, 0, len(data) - 1)
print(sorted_data)
print('Total ciclos:', ciclos)
print('Total pasos:', pasos)
