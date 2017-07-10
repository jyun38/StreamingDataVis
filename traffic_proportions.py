#Author: Maddy Kulke and Ji Young Yun

import csv #opening and iterating through rows
import itertools
with open('sensor_data.csv') as csvfile:
    csvReader = csv.reader(csvfile, delimiter = ',')

    car_id_lst = []
    gate_lst = []
    last_id = "nothing"
    gate_lst_index = 0
    new_car_id_lst = []
    new_gate_lst = []
    
    for row in itertools.islice (csvReader, 1, None):
        current_id = row[1]
        current_gate = row[3]
        if current_id == last_id:
            gate_lst[gate_lst_index].append(current_gate)
            last_id = current_id   
            
        else: 
            car_id_lst.append(current_id)
            gate_lst.append([])
            gate_lst_index = len(gate_lst) -1
            gate_lst[gate_lst_index].append(current_gate)
            last_id = current_id

    found = False 
    new_gate_lst.append(gate_lst[0])
    new_car_id_lst.append([car_id_lst[0]])
    
    for i in range(1, len(gate_lst)):
        for n in range(len(new_gate_lst)):
            if gate_lst[i] == new_gate_lst[n]:
                found = True
                new_car_id_lst[n].append(car_id_lst[i])

        if found != True:
            new_gate_lst.append(gate_lst[i])
            new_car_id_lst.append([car_id_lst[i]])
            
        found = False

    traffic_amounts = []

    for element in new_car_id_lst:
        traffic_amount = len(element)
        traffic_amounts.append(traffic_amount)
       
    with open('traffic_routes_updated.csv', 'w') as csvfile:
        csvWriter = csv.writer(csvfile, delimiter = ',')
        new_row = []
        for i in range(len(new_gate_lst)):
            for thing in new_gate_lst[i]:
                new_row.append(thing)
            # new_row.append(new_car_id_lst[i])
            new_row.append(len(new_car_id_lst[i]))
            csvWriter.writerow(new_row)
            new_row = []

