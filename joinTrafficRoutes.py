#Author: Yue Kuang and Ji Young Yun

import csv
import itertools

with open('new_traffic_routes.csv') as csvfile:
    csvReader = csv.reader(csvfile, delimiter = ',')
    route = []
    allRoutes = []
    output = []
    count = 0
    lst = []

    for row in itertools.islice (csvReader, 0, None):
    	for i in range(len(row)):
    		route.append(row[i])
    	allRoutes.append(route)
    	route = []

    with open('distinct_car.csv') as csvfile2:
    	csvReader2 = csv.reader(csvfile2, delimiter = ',')
    	for row2 in itertools.islice(csvReader2, 0, None):
    		car_id = row2[0]
    		for n in range(len(allRoutes)):
    			for j in range(len(allRoutes[n])):
    				if allRoutes[n][j] == car_id:
    					path = allRoutes[n][1]
    					output.append(lst)
    					output[count].append(car_id)
    					output[count].append(path)
    					lst = []
    					count+=1
    					
    	with open('joinedRoutes.csv', 'w') as csvfile3:
    		print("row is written")
    		csvWriter = csv.writer(csvfile3, delimiter = ',')
    		new_row = []
    		for line in range(len(output)):
    			for item in range(len(output[line])):
    				new_row.append(output[line][item])
    			csvWriter.writerow(new_row)
    			new_row = []

		
