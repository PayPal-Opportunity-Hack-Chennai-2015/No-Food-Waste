//
//  ServiceManager.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 28/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import Foundation

class ServiceManager {
    
    let baseURL = "192.168.117.34:8080"
    
    func createUser(name: String, phone: String, isVolunteer: Bool) {
        
        //http://host:port/register --data 'name=Rajiv&phone=9884098840&isVolunteer=true'
        //http://192.168.117.34:8080/user/create
        
        let components = NSURLComponents()
        components.scheme = "http";
        components.host = self.baseURL
        components.path = "/user/create";
        
        //let url = components.URL;
        
        let urlString = "http://192.168.117.34:8080/user/create"
        
        let url = NSURL(string: urlString)
        
        let request = NSMutableURLRequest(URL: url!)
        
        request.HTTPMethod = "POST"
        print(name)
        print(phone)
        print(isVolunteer)
        
        
    //    let postString = "username=\(name)&mobileNumber=\(phone)&isVolunteer=\(isVolunteer)&deviceId=\(phone)&deviceToken=\(phone)"
        
        let data = ["username":name,"mobileNumber":phone,"isVolunteer":isVolunteer,"deviceId":phone,"deviceToken":phone]
        
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        
        do {
            request.HTTPBody = try NSJSONSerialization.dataWithJSONObject(data, options: .PrettyPrinted)
        } catch {
            //handle error. Probably return or mark function as throws
            print(error)
            return
        }
        
        // request.HTTPBody = postString.dataUsingEncoding(NSUTF8StringEncoding);
        
        NSURLSession.sharedSession() .dataTaskWithRequest(request, completionHandler: { (data: NSData?, response:NSURLResponse?, error: NSError?) -> Void in
            print(data)
            print(response)
            print(error)
            
            do {
                if let results: NSDictionary = try NSJSONSerialization .JSONObjectWithData(data!, options: NSJSONReadingOptions.AllowFragments  ) as? NSDictionary {
                    
                    print(results)
                }
            } catch let error as NSError {
                print(error.localizedDescription)
            }

            
        }).resume()

    }
    
    func donateFood() {
        
    }
}