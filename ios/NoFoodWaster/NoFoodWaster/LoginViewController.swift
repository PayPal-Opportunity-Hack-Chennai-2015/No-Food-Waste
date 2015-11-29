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
    @IBOutlet weak var volunteer: UISwitch!
    
    var userDefault: NSUserDefaults?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // check if already register
        userDefault = NSUserDefaults(suiteName: "register")
        
        if let _ = userDefault?.objectForKey("phone") {
            performSegueWithIdentifier("mainSegue", sender: self)
        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        view.endEditing(true)
    }
    
    // MARK:- Login Action

    @IBAction func login(sender: AnyObject) {
        // service call
        
        if let phone = phoneTextField.text {
            
            let name = nameTextField.text
            
            userDefault?.setValue(phone, forKey: "phone")
            userDefault?.setValue(name, forKey: "name")
            userDefault?.setBool(volunteer.on, forKey: "isVolunteer")
            
            let serviceMgr = ServiceManager()
            serviceMgr.createUser(name!, phone: phone, isVolunteer: volunteer.on)
        }
    }
    
    func applyStyle() {
        view.backgroundColor = backgroundColor
        
        nameTextField.font = standardTextFont
        nameTextField.textColor = UIColor.whiteColor()
        
    }
}

