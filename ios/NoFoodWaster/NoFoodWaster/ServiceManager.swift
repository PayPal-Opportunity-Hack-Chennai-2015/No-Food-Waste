//
//  ServiceManager.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 28/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import Foundation

protocol ServiceManagerDelegate {
    func downloadDonateComplete(donate: Donate)
    func downloadConsumerComplete(consumer: Consumer)
}

class ServiceManager {
    
    var delegate: ServiceManagerDelegate?
    
    let baseURL = "http://192.168.117.235:8080"
    
    func createUser(name: String, phone: String, isVolunteer: Bool) {
        
        let urlString = baseURL + "/user/create"
        
        let data = ["username":name,"mobileNumber":phone,"isVolunteer":isVolunteer,"deviceId":phone,"deviceToken":phone]
        
        let request = buildRequest(urlString)
        
        buildRawData(data, request: request)
        
        processRequest(request)

    }
    
    func donateFood(mobile:String, status:String, foodType:String, quantity:String,
        latitude: Double, longitude: Double, address: String) {
        
        let urlString = baseURL + "/donate/create"
            
        let data = ["donorMobile":mobile,"donationStatus":"open","foodType":foodType,"quantity":quantity,"latitude":latitude,"longitude": longitude, "address":address]
            
        let request = buildRequest(urlString)
            
        buildRawData(data, request: request)
            
        processRequest(request)
    }
    
    func getDonateList() {
        
        let urlString = baseURL + "/donate/distance"
    
        let request = buildRequest(urlString,isPost: false)
        
        processDonateRequest(request)
    }
    
    func getConsumerList() {
        
        let urlString = baseURL + "/consumer"
        
        let request = buildRequest(urlString,isPost: false)
        
        processConsumerRequest(request)
    }
    
    func buildRequest(urlString: String, isPost:Bool = true) -> NSMutableURLRequest{
        let url = NSURL(string: urlString)
        
        let request = NSMutableURLRequest(URL: url!)
        
        if isPost {
            request.HTTPMethod = "POST"
            
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
            request.addValue("application/json", forHTTPHeaderField: "Accept")
        } else {
            request.HTTPMethod = "GET"
        }
        
        return request
    }
    
    func processRequest(request: NSMutableURLRequest) {
        NSURLSession.sharedSession() .dataTaskWithRequest(request, completionHandler: { (data: NSData?, response:NSURLResponse?, error: NSError?) -> Void in
            
            if error != nil {
                print(error?.localizedDescription)
                return
            }
            
            do {
                if let results: NSDictionary = try NSJSONSerialization .JSONObjectWithData(data!, options: NSJSONReadingOptions.AllowFragments  ) as? NSDictionary {
                    
                    print(results)
                }
            } catch let error as NSError {
                print(error.localizedDescription)
            }
            
            
        }).resume()
    }
    
    func processConsumerRequest(request: NSMutableURLRequest) {
        NSURLSession.sharedSession() .dataTaskWithRequest(request, completionHandler: { (data: NSData?, response:NSURLResponse?, error: NSError?) -> Void in
            
            if error != nil {
                print(error?.localizedDescription)
                return
            }
            
            do {
                if let results: NSArray = try NSJSONSerialization .JSONObjectWithData(data!, options: NSJSONReadingOptions.AllowFragments  ) as? NSArray {
                    
                    print(results)
                    
                    for item in results {
                        
                        let name = item["cosumerName"] as! String
                        let address = item["address"] as! String
                        let latitude = item["latitude"] as! String
                        let longitude = item["longitude"] as! String
                        let active = item["active"] as! String
                        
                        
                        let consumer = Consumer(name: name,address:address, latitude:
                            latitude, longitude: longitude, active: active)
                        self.delegate?.downloadConsumerComplete(consumer)
                    }
                }
            } catch let error as NSError {
                print(error.localizedDescription)
            }
            
            
        }).resume()
    }
    
    func processDonateRequest(request: NSMutableURLRequest) {
        NSURLSession.sharedSession() .dataTaskWithRequest(request, completionHandler: { (data: NSData?, response:NSURLResponse?, error: NSError?) -> Void in
            
            if error != nil {
                print(error?.localizedDescription)
                return
            }
            
            do {
                if let results: NSArray = try NSJSONSerialization .JSONObjectWithData(data!, options: NSJSONReadingOptions.AllowFragments  ) as? NSArray {
                    
                    
                    print(results)
                    
                    
                    for item in results {
                        let dict = item as! NSDictionary
                        let id = dict["id"] as! Int
                        let serves = dict["quantity"] as! String
                        let foodType = dict["foodType"] as! String
                        let latitude = dict["latitude"] as! String
                        let longitude = dict["longitude"] as! String
                        let mobile = dict["donorMobile"] as! String
                        let status = dict["donationStatus"] as! String
                        let address = dict["address"] as! String
                        let distance = dict["distance"] as! String
                        
                        let trimmedAddress = (address as NSString).stringByTrimmingCharactersInSet(NSCharacterSet.whitespaceAndNewlineCharacterSet())
                        
                        let donate = Donate(identifer: id, donorMobile: mobile,foodType:
                            foodType,serves:serves,address:trimmedAddress,latitude:latitude,
                            longitude:longitude,status: status, distance: distance)
                        self.delegate?.downloadDonateComplete(donate)
                        
                    }
                }
            } catch let error as NSError {
                print(error.localizedDescription)
            }
            
            
        }).resume()
    }
    
    func buildRawData(data:NSDictionary, request: NSMutableURLRequest) {
        do {
            request.HTTPBody = try NSJSONSerialization.dataWithJSONObject(data, options: .PrettyPrinted)
        } catch {
            //handle error. Probably return or mark function as throws
            print(error)
            return
        }
    }
}