import pickle
with open('100.pkl', 'rb') as file:
    data_100 = pickle.load(file)
with open('1000.pkl', 'rb') as file:
    data_1000 = pickle.load(file)
with open('10000.pkl', 'rb') as file:  
    data_10000 = pickle.load(file)

ciclos = 0
pasos = 0

def amontonarEnRama(arreglo, n, i):
    global pasos
    masGrande = i 
    izquierdo = 2 * i + 1
    derecho = 2 * i + 2

    pasos += 1 # Comparación
    if izquierdo < n and arreglo[izquierdo] > arreglo[masGrande]:
        masGrande = izquierdo

    pasos += 1 # Comparación
    if derecho < n and arreglo[derecho] > arreglo[masGrande]:
        masGrande = derecho

    pasos += 1 # Comparación
    if masGrande != i:
        arreglo[i], arreglo[masGrande] = arreglo[masGrande], arreglo[i]
        amontonarEnRama(arreglo, n, masGrande)

def heapSort(arreglo):
    global pasos
    global ciclos
    n = len(arreglo)

    for i in range(n // 2 - 1, -1, -1):
        ciclos += 1 # 1 ciclo
        amontonarEnRama(arreglo, n, i)

    for i in range(n - 1, 0, -1):
        ciclos += 1 # 1 ciclo
        arreglo[i], arreglo[0] = arreglo[0], arreglo[i]
        pasos += 1  # Intercambio
        amontonarEnRama(arreglo, i, 0)

ciclos = 0
pasos = 0 
heapSort(data_100)
print("Cantidad de pasos: ", pasos)
print("Cantidad de ciclos: ", ciclos)

ciclos = 0
pasos = 0
heapSort(data_1000)
print("Cantidad de pasos: ", pasos)
print("Cantidad de ciclos: ", ciclos)

ciclos = 0
pasos = 0
heapSort(data_10000)
print("Cantidad de pasos: ", pasos)
print("Cantidad de ciclos: ", ciclos)