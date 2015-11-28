//
//  MapLocationViewController.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 28/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import UIKit
import MapKit

class MapLocationViewController: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    
    var locationManager = CLLocationManager()
    var coordinate: CLLocationCoordinate2D?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        mapView.delegate = self
        locationManager.delegate = self
        
        let gestureRecogonizer = UILongPressGestureRecognizer(target: self, action: "addAnnotation:")
        gestureRecogonizer.minimumPressDuration = 1.0
        mapView.addGestureRecognizer(gestureRecogonizer)
    }

    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(true)
        
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestAlwaysAuthorization()
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
        
        mapView.showsUserLocation = true
    }
    
    func addAnnotation(gestureRecognizer:UIGestureRecognizer){
        
        mapView.removeAnnotations(mapView.annotations)
        
        let touchPoint = gestureRecognizer.locationInView(mapView)
        let newCoordinates = mapView.convertPoint(touchPoint, toCoordinateFromView: mapView)
        coordinate = newCoordinates
        let annotation = MKPointAnnotation()
        annotation.coordinate = newCoordinates
        mapView.addAnnotation(annotation)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let controller = segue.destinationViewController as! DonateViewController
        controller.coordinate = coordinate
    }
}

extension MapLocationViewController: CLLocationManagerDelegate {
    
    func locationManager(manager: CLLocationManager, didFailWithError error: NSError) {
        print("Error while updating location " + error.localizedDescription)
    }
}

extension MapLocationViewController: MKMapViewDelegate {

    func mapView(mapView: MKMapView, didUpdateUserLocation userLocation: MKUserLocation) {
        let loc:CLLocationCoordinate2D = userLocation.coordinate
        let region = MKCoordinateRegionMakeWithDistance(loc, 500, 500)
        mapView.setRegion(region, animated: true)
        locationManager.stopUpdatingLocation()
    }

}
