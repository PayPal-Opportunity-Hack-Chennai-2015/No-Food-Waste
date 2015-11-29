//
//  ThanksViewController.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 28/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import UIKit

class ThanksViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(animated: Bool) {
        
        super.viewWillAppear(true)
        
        self.navigationItem.setHidesBackButton(true, animated: false)
          navigationItem.title = ""
        
        let barButtonItem = UIBarButtonItem.init(title: "Home", style: .Done, target: self, action: "callHome")
        
        self.navigationItem.leftBarButtonItem = barButtonItem
    }
    
    func callHome() {
        
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let controller = storyboard.instantiateViewControllerWithIdentifier("MainViewController")
        navigationController?.pushViewController(controller, animated: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
