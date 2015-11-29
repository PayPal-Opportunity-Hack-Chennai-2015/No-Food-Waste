//
//  DonateViewController.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 28/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import UIKit
import MapKit

class DonateViewController: UIViewController {
    
    let locationManager = CLLocationManager()
    var coordinate: CLLocationCoordinate2D?
    
    var phone:String?
    var name:String?
    var volunteer:Bool?
    var activeField:UITextField?
    
    let donationStatus = "open"
    
    @IBOutlet weak var addressTextView: UITextView!

    @IBOutlet weak var foodSegmentControl: UISegmentedControl!
    @IBOutlet weak var locSwitch: UISwitch!
    @IBOutlet weak var serves: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: Selector("keyboardWillShow:"), name:UIKeyboardWillShowNotification, object: nil);
        NSNotificationCenter.defaultCenter().addObserver(self, selector: Selector("keyboardWillHide:"), name:UIKeyboardWillHideNotification, object: nil)
        
        if let coordinate = coordinate {
            locSwitch.on = false
            getPlaceName(coordinate.latitude, longitude: coordinate.longitude)
        } else {
            getCurrentLocation()
        }
        
        serves.delegate = self
        
        populateDefaults()
        
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(true)
        navigationItem.title = "Donate Food Now"
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewWillAppear(true)
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        navigationItem.title = nil
    }
    
    func populateDefaults() {

        let userDefault = NSUserDefaults(suiteName: "register")
        name = userDefault?.objectForKey("name") as? String
        phone = userDefault?.objectForKey("phone") as? String
        volunteer = userDefault?.boolForKey("isVolunteer")
    }
    
    func getPlaceName(latitude: Double, longitude: Double) {
        
        let coordinates = CLLocation(latitude: latitude, longitude: longitude)
        
        coordinate = CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
        
        CLGeocoder().reverseGeocodeLocation(coordinates, completionHandler: {(placemarks, error)->Void in
            
            if (error != nil) {
                print("Reverse geocoder failed with error" + error!.localizedDescription)
                return
            }
            
            if placemarks!.count > 0 {
                let pm = placemarks![0]
                self.displayLocationInfo(pm)
            } else {
                print("Problem with the data received from geocoder")
            }
        })
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        view.endEditing(true)
    }
    
    func keyboardWillShow(notification: NSNotification) {
        if ((activeField?.isKindOfClass(UITextField)) == nil) {
            self.view.frame.origin.y -= 160
        }

    }
    
    func keyboardWillHide(notification: NSNotification) {
        if ((activeField?.isKindOfClass(UITextField)) == nil) {
            self.view.frame.origin.y += 160
        }
        activeField = nil
    }
    
    deinit {
        NSNotificationCenter.defaultCenter().removeObserver(self);
    }
    
    @IBAction func deliver(sender: AnyObject) {
        
        let selectedIndex = foodSegmentControl.selectedSegmentIndex
        let foodType = foodSegmentControl.titleForSegmentAtIndex(selectedIndex)
        
        let serviceMgr = ServiceManager()
        serviceMgr.donateFood(phone!, status: donationStatus, foodType:foodType!, quantity: serves.text!, latitude: (coordinate?.latitude)!, longitude: (coordinate?.longitude)!, address: addressTextView.text)
    }
}

extension DonateViewController: CLLocationManagerDelegate {
    func locationManager(manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        
        coordinate = manager.location?.coordinate
        
        CLGeocoder().reverseGeocodeLocation(manager.location!, completionHandler: {(placemarks, error)->Void in
            
            if (error != nil) {
                print("Reverse geocoder failed with error" + error!.localizedDescription)
                return
            }
            
            if placemarks!.count > 0 {
                let pm = placemarks![0] 
                self.displayLocationInfo(pm)
            } else {
                print("Problem with the data received from geocoder")
            }
        })
    }
    
    func displayLocationInfo(placemark: CLPlacemark?) {
        if let containsPlacemark = placemark {
            //stop updating location to save battery life
            locationManager.stopUpdatingLocation()
            let locality = (containsPlacemark.locality != nil) ? containsPlacemark.locality : ""
            let postalCode = (containsPlacemark.postalCode != nil) ? containsPlacemark.postalCode : ""
            let administrativeArea = (containsPlacemark.administrativeArea != nil) ? containsPlacemark.administrativeArea : ""
            let country = (containsPlacemark.country != nil) ? containsPlacemark.country : ""
            
            let address = locality! + "\n" + administrativeArea! +  "\n" + postalCode! + "\n" + country!
            
            addressTextView.text = address
        }
        
    }
    
    @IBAction func currentLocation(sender: AnyObject) {
        
        let locationSwitch = sender as! UISwitch
        
        if locationSwitch.on {
            getCurrentLocation()
        } else {
            mapLocation()
        }
    }
    
    func getCurrentLocation() {
        
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
    }
    
    func mapLocation() {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let controller = storyboard.instantiateViewControllerWithIdentifier("MapLocationViewController")
        navigationController?.pushViewController(controller, animated: true)
    }
    
    
    func locationManager(manager: CLLocationManager, didFailWithError error: NSError) {
        print("Error while updating location " + error.localizedDescription)
    }
}

extension DonateViewController: UITextFieldDelegate {
    
    func textFieldShouldBeginEditing(textField: UITextField) -> Bool {
        activeField = textField
        return true
    }

}
