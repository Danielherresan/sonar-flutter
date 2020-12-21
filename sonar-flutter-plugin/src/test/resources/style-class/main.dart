
import 'dart:html';

import 'package:ValhallaApp/custom/widgets/fundation/colors.dart';
import 'package:ValhallaApp/custom/widgets/fundation/ourText.dart';
import 'package:ValhallaApp/custom/widgets/ourImage.dart';
import 'package:ValhallaApp/custom/widgets/our_floating_action_button.dart';
import 'package:flutter/material.dart';
class ElementView extends StatelessWidget {
  final String title;
  final String description;
  final String pathImage;
  ElementView ({Key key, this.title,this.description,this.pathImage}):super(key:key);
  @override
  Widget build(BuildContext context) {
    final element = Stack(
      alignment: Alignment(0.9, 1.1),
      children: <Widget>[
        Container(
          height: 150,
          margin: EdgeInsets.all(10.0),
          decoration: BoxDecoration(color: SCALE_02),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              OurImage(pathImage: this.pathImage,heightImage: 150.0, widthImage: 150.0,),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [Text(this.title),
                  Text(this.title),
                  NormalText(text: this.description,),
                  BcText(text: 'Nani'),Text(this.title), Text(this.title),Text(this.title),
                  Text ('Nani'),
                  ButtonElement(),
                ],
              )
            ],
          ),
        ),
        OurFloatingActionButton()
      ],
    );
    return element;
  }

}