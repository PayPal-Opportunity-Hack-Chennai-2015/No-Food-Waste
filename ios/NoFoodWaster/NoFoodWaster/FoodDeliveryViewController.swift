//
//  FoodDeliveryViewController.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 29/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import UIKit
import MapKit

class FoodDeliveryViewController: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        navigationItem.title = "Food Delivery Places"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
