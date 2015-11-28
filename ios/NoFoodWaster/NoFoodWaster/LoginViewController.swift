//
//  ViewController.swift
//  NoFoodWaster
//
//  Created by Ravi Shankar on 28/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var phoneTextField: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK:- Login Action

    @IBAction func login(sender: AnyObject) {
        // service call
    }
    
    func applyStyle() {
        view.backgroundColor = backgroundColor
        
        nameTextField.font = standardTextFont
        nameTextField.textColor = UIColor.whiteColor()
        
    }
    

}

