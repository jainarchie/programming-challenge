For the database: 

Install mysql on your system and create a test db

Create following table in the database:

CREATE  TABLE IF NOT EXISTS sensor_event_logs (event_id varchar(50),sensor_id varchar(50) not null,timestamp LONG not null,value Double not NULL,PRIMARY KEY(event_id));

Update your application properties in the resources:
Set your mysql url, user and password

Build the application using mvn clean install

Run the application using
java -jar <path>/target/challenge-0.0.1-SNAPSHOT.jar 

The application is up and running on port 8080

Sample curl for log event:

curl -X POST \
  http://localhost:8080/sensor/logEvent \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI4QzBEcTVOSFI2ZmRLY3lONVQzaTYtVUVpMEFlZ2ZSbUtkcHJFWTJ5a0JBIn0.eyJqdGkiOiJiMTA2N2RhMC1kNTcyLTRhMTQtYjMwMy05MzNiZjY5NjNkMDciLCJleHAiOjE1NzM4NTAyMDksIm5iZiI6MCwiaWF0IjoxNTczODE0MjExLCJpc3MiOiJodHRwczovL2JlYXpsZXktYXV0aC5xdWVzdG1hcmluZS5hcHAvYXV0aC9yZWFsbXMvbWFyaW5lIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6Ijk2ZmJiNzE3LTA0YjEtNGQ4MS1iNzlhLTJmODAyMzk2ODZlNSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImJyb2tlciIsIm5vbmNlIjoiYWVlZTZmZWYtMDczYy00MWIwLTgxMmYtZWM4NzIyYmIzZjMwIiwiYXV0aF90aW1lIjoxNTczODE0MjA5LCJzZXNzaW9uX3N0YXRlIjoiMTk2MTZlMzAtNTJkZC00NjZlLWFjOGMtOGIyZTg1MDUwMDBhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIiwiaHR0cHM6Ly9iZWF6bGV5LnF1ZXN0bWFyaW5lLmFwcCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiUG93ZXIgVXNlciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJIZW1hbnQgTmFncHVyZSIsInByZWZlcnJlZF91c2VybmFtZSI6ImhlbWFudC5uYWdwdXJlQGNvbmNpcnJ1cy5jb20iLCJnaXZlbl9uYW1lIjoiSGVtYW50IiwiZmFtaWx5X25hbWUiOiJOYWdwdXJlIiwiZW1haWwiOiJoZW1hbnQubmFncHVyZUBjb25jaXJydXMuY29tIn0.CmYL8xEZtPpohkIgExkzmLIKg8ca22WWtCqorkBFWMX5rxzowJXcoqP78oURY12cAIUhazYYQ7SDFNjBtqIv6EV0Sk4qUGg5vNzdsY4fC1j3S2e9m7ACoktPq-7lXdSC62ndx5yj4vLwGmfkQlJ60ryU4QttrrXD_8VgW-SGwawCsmnTn7VIOFtaEWT4itC5h5tykrjmevEi-4a8dUafvtzOTa3ErlUOOzTDJX7p3vosUwYYFuGstLvtobMHGtxFXHb16CYUuZNQGi9DrGYp-lFquCwOoofVR-lBICLrOKF409sM9IB9dqm20ozEX2XWInYqY4nNrYeENGh84bV1qQ' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 88' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: c9019a91-db42-4b0e-9c25-3c6e74999126,e82beeb0-1c2a-4ac6-b484-04e6d42f943f' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache' \
  -d '{"eventId":"eventId",
"sensorId":"sensor1",
"timestamp":"1573895980000",
"sensorValue":5}'



Sample curl for senseEvent:

curl -X POST \
  http://localhost:8080/sensor/senseEvent \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI4QzBEcTVOSFI2ZmRLY3lONVQzaTYtVUVpMEFlZ2ZSbUtkcHJFWTJ5a0JBIn0.eyJqdGkiOiJiMTA2N2RhMC1kNTcyLTRhMTQtYjMwMy05MzNiZjY5NjNkMDciLCJleHAiOjE1NzM4NTAyMDksIm5iZiI6MCwiaWF0IjoxNTczODE0MjExLCJpc3MiOiJodHRwczovL2JlYXpsZXktYXV0aC5xdWVzdG1hcmluZS5hcHAvYXV0aC9yZWFsbXMvbWFyaW5lIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6Ijk2ZmJiNzE3LTA0YjEtNGQ4MS1iNzlhLTJmODAyMzk2ODZlNSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImJyb2tlciIsIm5vbmNlIjoiYWVlZTZmZWYtMDczYy00MWIwLTgxMmYtZWM4NzIyYmIzZjMwIiwiYXV0aF90aW1lIjoxNTczODE0MjA5LCJzZXNzaW9uX3N0YXRlIjoiMTk2MTZlMzAtNTJkZC00NjZlLWFjOGMtOGIyZTg1MDUwMDBhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIiwiaHR0cHM6Ly9iZWF6bGV5LnF1ZXN0bWFyaW5lLmFwcCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiUG93ZXIgVXNlciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJIZW1hbnQgTmFncHVyZSIsInByZWZlcnJlZF91c2VybmFtZSI6ImhlbWFudC5uYWdwdXJlQGNvbmNpcnJ1cy5jb20iLCJnaXZlbl9uYW1lIjoiSGVtYW50IiwiZmFtaWx5X25hbWUiOiJOYWdwdXJlIiwiZW1haWwiOiJoZW1hbnQubmFncHVyZUBjb25jaXJydXMuY29tIn0.CmYL8xEZtPpohkIgExkzmLIKg8ca22WWtCqorkBFWMX5rxzowJXcoqP78oURY12cAIUhazYYQ7SDFNjBtqIv6EV0Sk4qUGg5vNzdsY4fC1j3S2e9m7ACoktPq-7lXdSC62ndx5yj4vLwGmfkQlJ60ryU4QttrrXD_8VgW-SGwawCsmnTn7VIOFtaEWT4itC5h5tykrjmevEi-4a8dUafvtzOTa3ErlUOOzTDJX7p3vosUwYYFuGstLvtobMHGtxFXHb16CYUuZNQGi9DrGYp-lFquCwOoofVR-lBICLrOKF409sM9IB9dqm20ozEX2XWInYqY4nNrYeENGh84bV1qQ' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 88' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -H 'Postman-Token: c9019a91-db42-4b0e-9c25-3c6e74999126,e82beeb0-1c2a-4ac6-b484-04e6d42f943f' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache' \
  -d '{"eventId":"eventId",
"sensorId":"sensor1",
"timestamp":"1573895980000",
"sensorValue":5}'

