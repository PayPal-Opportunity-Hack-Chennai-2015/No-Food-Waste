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
    
    @IBOutlet weak var descriptionLabel: UILabel!
    
    var consumers = [Consumer]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        navigationItem.title = "Food Delivery Places"
        
        applyStyle()
        let serviceMgr = ServiceManager()
        serviceMgr.delegate = self
        
        serviceMgr.getConsumerList()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func applyStyle() {
        descriptionLabel.backgroundColor = backgroundColor
        view.backgroundColor = backgroundColor
    }
}

extension FoodDeliveryViewController: ServiceManagerDelegate {
    func downloadDonateComplete(donate: Donate) {
        // this should not be here
    }
    
    func downloadConsumerComplete(consumer: Consumer) {
        dispatch_async(dispatch_get_main_queue()) { () -> Void in
            self.consumers.append(consumer)
            //mapView.addAnnotations(<#T##annotations: [MKAnnotation]##[MKAnnotation]#>)
        }
    }
}
