import numpy as np
import matplotlib.pyplot as plt

# Data
x_dataSize = [np.log10(1), np.log10(10), np.log10(100), np.log10(1000), np.log10(10000), np.log10(100000)]
y_insertion = [np.log10(100), np.log10(413), np.log10(1052), np.log10(2015), np.log10(3198), np.log10(4461)]
y_theoretical = [np.log10(size) for size in [2, 10, 100, 1000, 10000, 100000]]

# Plot
plt.figure(figsize=(10, 6))
plt.plot(x_dataSize, y_insertion, marker='o', label='Actual Searching Operations')
plt.plot(x_dataSize, y_theoretical, marker='s', label='Theoretical Complexity (log scale)')

# Add labels and title
plt.xlabel('Log Dataset Size')
plt.ylabel('Log Number of Operations')
plt.title('Comparison of Actual and Theoretical Complexity of AVL Tree Searching')
plt.grid(True)
plt.legend()

# Set axis limits
plt.xlim(min(x_dataSize), max(x_dataSize))  # Set x-axis limits to the minimum and maximum values in x_dataSize
plt.ylim(min(min(y_insertion), min(y_theoretical)), max(max(y_insertion), max(y_theoretical)))  # Set y-axis limits to encompass both datasets

# Show plot
plt.show()
