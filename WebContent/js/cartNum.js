function number()
    {
        var char = String.fromCharCode(event.keyCode)
        var re = /[0-9]/g
        event.returnValue = char.match(re) != null ? true : false
    }
    function filterInput()
    {
        if (event.type.indexOf("key") != -1)
        {
            var re = /37|38|39|40/g
            if (event.keyCode.toString().match(re)) return false
                }
        event.srcElement.value = event.srcElement.value.replace(/[^0-9]/g, "")
    }
    function filterPaste()
    {
        var oTR = this.document.selection.createRange()
        var text = window.clipboardData.getData("text")
        oTR.text = text.replace(/[^0-9]/g, "")
    }
    $(function(){  
        var t = $("#text_box");  
        
        $("#min").click(function(){  
               if (parseInt(t.val())>1) {                     //判断数量值大于1时才可以减少  
                    t.val(parseInt(t.val())-1)  
                    $("#add").removeAttr("disabled");           //当按减1时，解除$("#add")不可读状态
                    $("#min").removeAttr("disabled");           //当按加1时，解除$("#min")不可读状态 
                }else{  
                    $("#min").attr("disabled","disabled")        //当$("#min")为1时，$("#min")不可读状态  
                }    
        })  
        $("#add").click(function(){
        	var tt=document.getElementById("num");
        	var hehe=tt.getElementsByTagName("span");
        	var hh=hehe[0].innerText;
        	//alert(parseInt(t.val())+","+hh)
            t.val(parseInt(t.val())+1)
            $("#add").removeAttr("disabled");           //当按减1时，解除$("#add")不可读状态
            $("#min").removeAttr("disabled");           //当按加1时，解除$("#min")不可读状态     
            if(parseInt(t.val())>hh-1){
            	alert("已经超出库存总量")
            	$("#add").attr("disabled","disabled") 
            }
        })   
    })  