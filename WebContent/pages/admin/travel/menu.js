			function fnCall(from)
			{
								
			    var act="TravelConfigure.action?menuType="+from;
				if(from=='scheme'){
					document.form1.action = from+act;
				}
				else if(from=='option'){
					document.form1.action = from+act;
				}
				else if(from=='linkoption'){
					document.form1.action = from+act;
				}
				else if(from=='linkcoverage'){
					document.form1.action = from+act;
				}
				else if(from=='coverage'){
					document.form1.action = from+act;
				}
				else if(from=='discountrate'){
					document.form1.action = from+act;
				}
				else if(from=='relationdiscount'){
					document.form1.action = from+act;
				}
				else if(from=='premiumrate'){
					document.form1.action = from+act;
				}
					document.form1.submit();
		}	