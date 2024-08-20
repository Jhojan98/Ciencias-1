import random,pickle
# Function to generate shuffled arrays containing all numbers within a specific range and save to .pkl files
def generate_and_save_shuffled_array(file_name, upper_limit):
    array = list(range(1, upper_limit + 1))  # Generate array with all numbers in the range
    random.shuffle(array)  # Shuffle the array
    with open(f"{file_name}.pkl", 'wb') as file:
        pickle.dump(array, file)

# Generating and saving shuffled arrays containing all numbers within the specified ranges
limits = [10, 100, 1000, 10000]
for upper_limit in limits:
    generate_and_save_shuffled_array(str(upper_limit), upper_limit)

# Shuffled arrays generated and saved in .pkl files: '10.pkl', '100.pkl', '1000.pkl', '10000.pkl'

