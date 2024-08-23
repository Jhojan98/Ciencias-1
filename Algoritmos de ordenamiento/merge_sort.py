# -*- coding: utf-8 -*-
"""Merge Sort.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1dGjC-hVyxIQppHWVtYyuBeAOjEwhir0o
"""

step_count = 0
import pickle
with open('100.pkl', 'rb') as file:
    arr_100 = pickle.load(file)
with open('1000.pkl', 'rb') as file:
    arr_1000 = pickle.load(file)
with open('10000.pkl', 'rb') as file:
    arr_10000 = pickle.load(file)
    
def merge(ordArrIzq, ordArrDer):
    global step_count
    arrResul = []
    while len(ordArrIzq) > 0 and len(ordArrDer) > 0:
        step_count += 1
        if ordArrIzq[0] < ordArrDer[0]:
            arrResul.append(ordArrIzq[0])
            ordArrIzq.pop(0)
        else:
            arrResul.append(ordArrDer[0])
            ordArrDer.pop(0)

    while len(ordArrIzq) > 0:
        step_count += 1
        arrResul.append(ordArrIzq[0])
        ordArrIzq.pop(0)

    while len(ordArrDer) > 0:
        step_count += 1
        arrResul.append(ordArrDer[0])
        ordArrDer.pop(0)

    return arrResul

def mergeSort(arr):
    global step_count
    if len(arr) == 1:
        return arr
    mitad = len(arr) // 2
    arrIzq = arr[:mitad]
    arrDer = arr[mitad:]
    ordArrIzq = mergeSort(arrIzq)
    ordArrDer = mergeSort(arrDer)
    return merge(ordArrIzq, ordArrDer)
 
step_count = 0
mergeSort(arr_100)
print(f"Numero de pasos para un arreglo de 100 elementos: {step_count}")

step_count = 0
mergeSort(arr_1000)
print(f"Numero de pasos para un arreglo de 1000 elementos: {step_count}")

step_count = 0
mergeSort(arr_10000)
print(f"Numero de pasos para un arreglo de 10000 elementos: {step_count}")
