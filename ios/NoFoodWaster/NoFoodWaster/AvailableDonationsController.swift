//
//  AvailableDonationsController.swift
//  NoFoodWaste
//
//  Created by Ravi Shankar on 29/11/15.
//  Copyright © 2015 Ravi Shankar. All rights reserved.
//

import UIKit

class AvailableDonationsController: UIViewController {
    
    let cellIdentifier = "availableDonationsCell"

    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.dataSource = self
        tableView.delegate = self
        
        navigationItem.title = "Available Dontations"
        descriptionLabel.backgroundColor = backgroundColor
        view.backgroundColor = backgroundColor
        
        resetChecks()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableViewStyle(cell: UITableViewCell) {
        cell.contentView.backgroundColor = backgroundColor
        cell.backgroundColor = backgroundColor
        
        cell.textLabel?.font =  UIFont(name: "HelveticaNeue-Medium", size: 15)
        cell.textLabel?.textColor = textColor
        cell.textLabel?.backgroundColor = backgroundColor
        cell.textLabel?.numberOfLines = 2
        
        cell.detailTextLabel?.font = UIFont.boldSystemFontOfSize(15)
        cell.detailTextLabel?.textColor = UIColor.grayColor()
        cell.detailTextLabel?.backgroundColor = backgroundColor
    }

}

extension AvailableDonationsController: UITableViewDataSource, UITableViewDelegate {
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier, forIndexPath: indexPath)
        
        cell.textLabel?.text = "Please select the location you would like to pick up the donated food from"
        
        tableViewStyle(cell)
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        if let cell = tableView.cellForRowAtIndexPath(indexPath) {
            
            resetChecks()
            
            if cell.accessoryType == .Checkmark
            {
                cell.accessoryType = .None
            }
            else
            {
                cell.accessoryType = .Checkmark
            }
        }
    }
    
    func resetChecks()
    {
        for i in 0...tableView.numberOfSections-1
        {
            for j in 0...tableView.numberOfRowsInSection(i)-1
            {
                if let cell = tableView.cellForRowAtIndexPath(NSIndexPath(forRow: j, inSection: i)) {
                    cell.accessoryType = .None
                }
                
            }
        }
    }
}
