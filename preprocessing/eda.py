import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Load the cleaned data
data = pd.read_csv('/home/kushal/cleaned_weather_data.csv')

# Visualize Rainfall over time
plt.figure(figsize=(12, 6))
sns.lineplot(x='Date', y='Precipitation', data=data)
plt.title('Rainfall over Time')
plt.xlabel('Date')
plt.ylabel('Precipitation (mm)')
plt.xticks(rotation=45)
plt.show()

# Visualize Temperature over time
plt.figure(figsize=(12, 6))
sns.lineplot(x='Date', y='Temperature', data=data, color='orange')
plt.title('Temperature over Time')
plt.xlabel('Date')
plt.ylabel('Temperature (°C)')
plt.xticks(rotation=45)
plt.show()

# Summary statistics to analyze
print(data.describe())

