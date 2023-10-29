# DNS-Server
This is project for Computer networks 5th sem, a DNS- Server using microservices in java using Springboot framework

## About

Backend is implemented with Springboot using microservices
Frontend is implemented in react

#### Backend
- There are 3 services
   1. Rootservice
   2. Top Level Domain
   3. NameServer
      - #### Rootservice
         This service takes the website name and returns domain name to TopLevelDomain
      - #### Top Level Domain
         This service takes domain name {.com,.org....} from Rootservice and checks its validity and return response to Nameserver
      - #### Nameserver
         This service takes response from Top Level Domain and returns ip-address for given website if it is valid else throws error
- I have also integrated Redis for RateLimiting, just to prevent from DoS attack, if a domain is continuousy making Api Request then we are blocking that request
  
