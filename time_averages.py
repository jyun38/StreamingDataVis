def time_average(csvName):


'''reads csv with sensor data and writes CSV with start gate, end gate, avg. time
and total number of vehicles for each unique route'''

    
    import csv
    with open(csvName) as csvfile:
        breakfastReader = csv.reader(csvfile, delimiter = ',')







        gate_lst = []
        
        
        

        
        for row in breakfastReader:
            current_gate = row[4]
            found = False 
            
            if row[10] != 'n' and row[10] != 'timeToNext':
                for element in gate_lst:
                    if element[0] == past_gate and element[1] == current_gate:
                        found = True
                        element.append(int(row[10]))
                        

                if found == False:
                    gate_lst.append([past_gate, current_gate])
                    gate_lst[-1].append(int(row[10]))
                        
                    

            past_gate = row[4]



           
        one_average = []
        avg_lst = []
        
        

        for element in gate_lst:
            one_average.append(element[0])
            one_average.append(element[1])
            num_lst = element[2:]
            num_traffic = len(num_lst)
            average = sum(num_lst)/ num_traffic
            one_average.append(average)
            one_average.append(num_traffic)
            avg_lst.append(one_average)
            one_average = []

            
        #for item in avg_lst:
            #print(item[0], item[1], "average:", item[2], "num traffic:", item[3])

            
            



            
    


    


    with open('traffic averages.csv', "w") as csvfile:
        csvWriter = csv.writer(csvfile, delimiter = ',')

        new_row = []

        for item in avg_lst:
            for thing in item:
                new_row.append(str(thing))
            csvWriter.writerow(new_row)
            new_row = []
        
        

          


            

time_average('gateData(diff).csv')
