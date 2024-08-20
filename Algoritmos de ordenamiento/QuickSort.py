import pickle
with open('100.pkl', 'rb') as file:
    data = pickle.load(file)

ciclos = 0
pasos = 0

def quicksort(array, indice_inicial, indice_pivote):
    global ciclos, pasos
    valor_pivote = array[indice_pivote]
    puntero_izquierdo = indice_inicial - 1
    intercambiado = False

    for indice_actual in range(indice_inicial, indice_pivote):
        ciclos += 1
        if array[indice_actual] > valor_pivote:
            intercambiado = True
            while indice_actual <= puntero_izquierdo:
                ciclos += 1
                if array[puntero_izquierdo] < valor_pivote:
                    pasos += 1
                    array[indice_actual], array[puntero_izquierdo] = array[puntero_izquierdo], array[indice_actual]
                    puntero_izquierdo -= 1
                    break
                puntero_izquierdo -= 1

        if indice_actual > puntero_izquierdo or not intercambiado and indice_actual == indice_pivote - 1:
            pasos += 1
            if intercambiado:
                pasos += 1
                array[indice_actual], array[indice_pivote] = valor_pivote, array[indice_actual]
            else:
                indice_actual = indice_pivote

            if (indice_actual - 1) > indice_inicial:
                pasos += 1
                quicksort(array, indice_inicial, indice_actual - 1)
            if (indice_actual + 1) < indice_pivote:
                pasos += 1
                quicksort(array, indice_actual + 1, indice_pivote)

            return array

data_ordenada = quicksort(data, 0, len(data) - 1)
print(data_ordenada)
print('Ciclos:', ciclos)
print('Pasos:', pasos)