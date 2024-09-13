def ordenar_matriz(matriz):
    n = len(matriz)
    nueva_m=[[] for _ in range(n)]
    W=[]
    V=[]
    num_pezz=[]
    for i in range(0,n):
        m=len(matriz[i])
        for j in range(0,m):
            if j==0:
                nueva_m[i].append(matriz[i][j])
                V.append(matriz[i][j])
                W.append(j+1)
                num_pezz.append(i)
            else:
                nueva_m[i].append(matriz[i][j] + nueva_m[i][j-1])
                V.append(matriz[i][j] + nueva_m[i][j-1])
                W.append(j+1)
                num_pezz.append(i)
    print(nueva_m)
    print(W)
    print(V)
    print(num_pezz)
   
    return nueva_m, W, V, num_pezz

# n es el total de dulces que exiten y M es el peso maximo(maxima cantidad de dulces a todmar)
#W[]= pesos de cada objeto
#V[]= Valores de cada objeto
#M= peso maximo 
#n= total de objetos
def knapsack_dy_prog(W, V, M, n,num_pezz, nueva_m):
    # Inicializa la matriz B con ceros (n+1 filas y M+1 columnas)
    B = [[0 for _ in range(M + 1)] for _ in range(n + 1)]

    # Rellenar la matriz B según la programación dinámica
    for i in range(1, n + 1):  # Itera sobre los artículos
        for j in range(1, M + 1):  # Itera sobre la capacidad de la mochila
            if W[i - 1] <= j:  # Si el peso del artículo i cabe en la mochila
                B[i][j] = max(B[i - 1][j], V[i - 1] + B[i - 1][j - W[i - 1]])
            else:  # Si no cabe, no se incluye el artículo
                B[i][j] = B[i - 1][j]

    # Imprimir el valor máximo que se puede obtener
    print("Max Value:", B[n][M])
    print(B)
    # Encontrar los paquetes seleccionados
    print("Selected Packs:")
    capacity_remaining = M  # Capacidad restante de la mochila
    while n > 0:
        if B[n][capacity_remaining] != B[n - 1][capacity_remaining]:  # Si el artículo fue seleccionado
            print(f"\tPackage {n} with W = {W[n - 1]} and Value = {V[n - 1]}")
            var= num_pezz[n-1]
            for i in range(0,len(num_pezz)):
                if num_pezz[i]== var:
                    #nueva_m.pop(i)
                    num_pezz.pop(i)
            print(num_pezz[n-1])
            capacity_remaining -= W[n - 1]  # Restar el peso del artículo seleccionado
        n -= 1  # Mover al siguiente artículo
    print(nueva_m)
    print(num_pezz)

if __name__ == "__main__":
    matriz= [[10,5,2,3,1],[1,5,20,3,1],[11,50,2,3,10]]
    #W = [1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5]  # Pesos de los artículos
    #V = [10, 15, 17, 20, 21, 1, 6, 26, 29, 30, 1, 51, 53, 56, 66]  # Valores de los artículos
    M = 3# Capacidad máxima de la mochila
    nueva_m, W, V , num_pezz = ordenar_matriz(matriz)
    n = len(W)
    knapsack_dy_prog(W, V, M, n, num_pezz, nueva_m)
