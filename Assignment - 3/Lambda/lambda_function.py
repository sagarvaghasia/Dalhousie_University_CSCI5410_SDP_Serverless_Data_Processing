#https://github.com/venkateshkodumuri/Lex_Chatbot_to_fetch_data_from_dynamodb/blob/main/lambda_function.py
#https://stackoverflow.com/questions/36780856/complete-scan-of-dynamodb-with-boto3

import json
import boto3
from boto3.dynamodb.conditions import Key, Attr
client = boto3.resource('dynamodb')
table = client.Table('Student')
result = {}
intent=""
name=""
email=""
Id=""
slots=""

def lambda_handler(event, context):
    print(event)
    global intent
    global slots
    intent=event['sessionState']['intent']['name']
    print("intent inside handler")
    print(intent)

    if event['invocationSource'] == 'FulfillmentCodeHook':
        slots=event['sessionState']['intent']['slots']
        email=slots['Email']['value']['originalValue']
        print("email")
        print(email)
        name=slots['Name']['value']['originalValue']
        print("name")
        print(name)
        if intent=='StudentValidation':
            verifyStudentDetails(email,name)
            print(result)
            return result

def verifyStudentDetails(emailFrontend,nameFrontend):
    print("inside get data from db")
    response = table.scan(FilterExpression=Attr('Email').eq(emailFrontend) & Attr('Name').eq(nameFrontend))
    key='Item'
    value = key in response.keys()
    if response['Count']==1:
        print("inside if that get data and verifes data to be matched")
        msg = "details verified"
        global result
        result = {
                    "sessionState": {
                        "dialogAction": {
                            "type": "Close"
                        },
                        "intent": {
                            'name':intent,
                            'slots': slots,
                            'state':'Fulfilled'
                        }
                    },
                    "messages": [
                        {
                            "contentType": "PlainText",
                            "content": msg
                        }
                    ]
                }
        return result

    else:
        print("isnide else of match if ")
        msg = "details couldn't be verified: name did not match"
        print("didnt find the account")
        message="Sorry I can't find your details in our records : email does not exists in our database"
        result =  {
                    "sessionState": {
                        "dialogAction": {
                            "type": "Close"
                        },
                        "intent": {
                            'name':intent,
                            'slots': slots,
                            'state':'Fulfilled'
                        }
                    },
                    "messages": [
                        {
                            "contentType": "PlainText",
                            "content": message
                        }
                    ]
                }
        return result

   


