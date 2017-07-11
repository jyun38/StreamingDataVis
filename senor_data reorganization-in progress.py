#Author: Maddy Kulke


import csv #opening and iterating through rows
import itertools

def organize_csv():
    

    with open('Lekagul Sensor Data-2.csv') as csvfile:
        csvReader = csv.reader(csvfile, delimiter = ',')

    with open('sensor_csv_updated.csv', 'w') as csvfile:
            csvWriter = csv.writer(csvfile, delimiter = ',')

    new_row = []
    last_id = "nothing"

    
    for row_now in itertools.islice (csvReader, 1, None):
        id_now = row_now[1]
        if id_now != last_id:
            last_row = row_now
            last_id = id_now   
            
        else:
            
            to_append_lst = [id_now, last_row[0], row_now[0], last_row[3], row_now[3]]

            for datum in to_append_lst:
                new_row.append(datum)

            csvWriter.writerow(new_row)
            new_row = []
            last_row = row_now

organize_csv()



                

