import pickle

array = [2, 4, 5, 1, 3]
with open('100.pkl', 'wb') as file:
    pickle.dump(array, file)
