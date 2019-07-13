//
//  ViewController.swift
//  hello world
//
//  Created by Noriki Ikeuchi on 2019/05/16.
//  Copyright © 2019 Noriki Ikeuchi. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBAction func button(_ sender: Any) {
        let num = arc4random_uniform(4)
        var result = "結果"
        switch num {
        case 0:
            result = "大吉"
        case 1:
            result = "小吉"
        case 2:
            result = "中吉"
        case 3:
            result = "凶"
        default:
            break
    }
    
}
}
