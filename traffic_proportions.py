
#Author: Maddy Kulke



def traffic_proportions(csvfile):

    import csv #opening and iterating through rows
    import itertools
    with open('Lekagul Sensor Data-2.csv') as csvfile:
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
                gate_lst_index = len(gate_lst) -1
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
        for element in car_id_lst:
            traffic_amount = len(element)
            traffic_amounts.append(traffic_amount)
           
            
        print(len(new_car_id_lst), len(new_gate_lst))

        with open('traffic_routes.csv', 'w') as csvfile:
            csvWriter = csv.writer(csvfile, delimiter = ',')
            new_row = []
            for i in range(len(gate_lst)-1):
                for thing in new_gate_lst[i-1]:
                    new_row.append(thing)
                    #print(i)
                new_row.append(new_car_id_lst[i])
                
                csvWriter.writerow(new_row)

                new_row = []

               

            

            
                
                    
                
                
                   
                    

                    
                


        
        
        


        
        
                    
                
                
            
                

        
        

            
                
                
            
            
        

traffic_proportions('timestamp.csv')
