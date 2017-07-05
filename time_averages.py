
#Author: Maddy Kulke




def time_average(csvName):


'''reads csv with sensor data and writes CSV with start gate, end gate, avg. time
and total number of vehicles for each unique route'''


 #import csv and read   
    import csv
    with open(csvName) as csvfile:
        csvReader = csv.reader(csvfile, delimiter = ',')

#empty gate lst 
        gate_lst = []
        
'''loop iterates through gate_lst, if a certain combo of
start and end gate is not yet listed, this combination is appended to the lst
as two seperate items of a small lst within the bigger lst'''
        
        for row in csvReader:
            current_gate = row[4]
            found = False 
            
            if row[10] != 'n' and row[10] != 'timeToNext':
                for element in gate_lst:
                    if element[0] == past_gate and element[1] == current_gate:
                        found = True
                        element.append(int(row[10])) #if found, time data is added to smaller lst
                if found == False:
                    gate_lst.append([past_gate, current_gate])
                    gate_lst[-1].append(int(row[10])) #if not found, gate combo is appended as new smaller lst and time data added to this lst
            past_gate = row[4] #current gate becomes past gate before loop moves to next row


'''average of times between gates and total amount of traffic is calculated'''
           
        one_average = [] #small lst 
        avg_lst = []   #big lst 
        
        

        for element in gate_lst:
            one_average.append(element[0]) #start and end gate added to small lst
            one_average.append(element[1]) 
            num_lst = element[2:]
            num_traffic = len(num_lst)   #num traffic calculated
            average = sum(num_lst)/ num_traffic #average time between start and end gates calculated
            one_average.append(average) #average and num traffic added to small list
            one_average.append(num_traffic)
            avg_lst.append(one_average) #small lst feeds into big lst then beomes empty
            one_average = []

''' Writing csv file'''


        with open('traffic averages.csv', "w") as csvfile:
        csvWriter = csv.writer(csvfile, delimiter = ',')

        new_row = []

        for item in avg_lst:
            for thing in item:
                new_row.append(str(thing)) #converts all items in row to string
            csvWriter.writerow(new_row) #writes row 
            new_row = []
        
        

          


            

time_average('gateData(diff).csv')
