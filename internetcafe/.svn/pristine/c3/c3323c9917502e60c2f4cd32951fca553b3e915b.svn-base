/**
* This file is part of Qunee for HTML5.
* Copyright (c) 2016 by qunee.com
**/
if(!window.getI18NString){getI18NString = function(s){return s;}}
;

if(!Q.Element.prototype.initAlarmBalloon){
    Q.Element.prototype.initAlarmBalloon = function(){
        var alarmUI = new Q.LabelUI();
        alarmUI.position = Q.Position.CENTER_TOP;
        alarmUI.anchorPosition = Q.Position.LEFT_BOTTOM;
        alarmUI.border = 1;
        alarmUI.backgroundGradient = Q.Gradient.LINEAR_GRADIENT_VERTICAL;
        alarmUI.padding = new Q.Insets(2, 5);
        alarmUI.showPointer = true;
        alarmUI.offsetY = -10;
        alarmUI.offsetX = -10;
        alarmUI.rotatable = false;
        alarmUI.showOnTop = true;
        this._alarmBalloon = alarmUI;
    }
    Q.Element.prototype._checkAlarmBalloon = function(){
        if(!this.alarmLabel || !this.alarmColor){
            if(this._alarmBalloon){
                this.removeUI(this._alarmBalloon);
            }
            return;
        }
        if(!this._alarmBalloon){
            this.initAlarmBalloon();
        }
        this._alarmBalloon.data = this.alarmLabel;
        this._alarmBalloon.backgroundColor = this.alarmColor;
        if(this.addUI(this._alarmBalloon) === false){
            this.invalidate();
        }
    }
    Object.defineProperties(Q.Element.prototype, {
        alarmLabel: {
            get: function(){
                return this._alarmLabel;
            },
            set: function(label){
                if(this._alarmLabel == label){
                    return;
                }
                this._alarmLabel = label;
                this._checkAlarmBalloon();
            }
        },
        alarmColor: {
            get: function(){
                return this._alarmColor;
            },
            set: function(color){
                if(this._alarmColor == color){
                    return;
                }
                this._alarmColor = color;
                this.setStyle(Q.Styles.RENDER_COLOR, color);
                this._checkAlarmBalloon();
            }
        }
    })
}
var AlarmSeverity = {
    CRITICAL: {color: Q.toColor(0xEEFF0000), sortName: "C"},
}



	$("#tuopu").on('click',function(){
    var canvas1 = document.getElementById('canvas');
	var canvas2 = document.getElementById('canvas2');
    var canvas3 = document.getElementById('canvas3');
    var ret = document.getElementById('ret');
	var sitetitle = document.getElementById('sitetitle');
		canvas1.style.display = "block";
		canvas2.style.display = "none";
		canvas3.style.display = "none";
		ret.style.display = "none";
        sitetitle.style.display = "block";		
		$("#tuopu").parent().removeClass();
		$("#firm").parent().removeClass();
		$("#tuopu").parent().addClass('ui-state-default ui-corner-top ui-tabs-active ui-state-active');
    $("#firm").parent().addClass('ui-state-default ui-corner-top');
    });
	$("#firm").on('click',function(){
       var canvas1 = document.getElementById('canvas');
	   var canvas2 = document.getElementById('canvas2');
       var canvas3 = document.getElementById('canvas3');
       var ret = document.getElementById('ret');
	   var sitetitle = document.getElementById('sitetitle');
		canvas3.style.display = "block";
		canvas2.style.display = "none";
		canvas1.style.display = "none";
		ret.style.display = "none";
        sitetitle.style.display = "none";
        canvas2.innerHTML=""; 		
		$("#tuopu").parent().removeClass();
		$("#firm").parent().removeClass();
		$("#tuopu").parent().addClass('ui-state-default ui-corner-top');
    $("#firm").parent().addClass('ui-state-default ui-corner-top ui-tabs-active ui-state-active');
    });
	 $("#ret").on('click',function(){
		var canvas1 = document.getElementById('canvas');
	    var canvas2 = document.getElementById('canvas2');
        var canvas3 = document.getElementById('canvas3');
		var ret = document.getElementById('ret');
		canvas3.style.display = "none";
		canvas2.style.display = "none";
		canvas1.style.display = "block";
		ret.style.display = "none";
		canvas2.innerHTML=""; 
	 });
	 $("#photo").on('click',function(){
		window.open(address);
	 });

