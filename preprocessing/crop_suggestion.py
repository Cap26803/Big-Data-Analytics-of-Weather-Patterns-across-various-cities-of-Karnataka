import pandas as pd

# Load the cleaned data
data = pd.read_csv('/home/kushal/cleaned_weather_data.csv')

# Filter data for high rainfall (e.g., greater than 100 mm in a month)
high_rainfall_data = data[data['Precipitation'] > 100]

# Assume that crops require average temperatures and rainfall for specific seasons
def suggest_crop(temperature, rainfall):
    if temperature > 20 and rainfall > 100:
        return "Rice (Kharif Season)"
    elif temperature < 20 and rainfall > 50:
        return "Wheat (Rabi Season)"
    elif temperature > 25 and rainfall > 50:
        return "Sugarcane"
    else:
        return "Pulses (Kharif Season)"

# Apply the crop suggestion logic
high_rainfall_data['Suggested Crop'] = high_rainfall_data.apply(
    lambda row: suggest_crop(row['Temperature'], row['Precipitation']), axis=1)

# Save the output with crop suggestions
high_rainfall_data.to_csv('/home/kushal/rainfall_crop_suggestion.csv', index=False)

print("Crop suggestions based on rainfall and temperature have been saved!")

