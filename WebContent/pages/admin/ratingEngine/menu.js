function fnCall(from) {	
	    var act="Rating.action?menuType="+from;
	    var actn="MotorAdmin.action?menuType="+from;
		if(from=='conveyance')
			document.info.action = from+act;
		else if(from=='countrymaster')
			document.info.action = from+act;
		else if(from=='country')
			document.info.action = from+act;
		else if(from=='commoditymaster')
			document.info.action = from+act;
		else if(from=='covercommomaster')
			document.info.action = from+act;
		else if(from=='categorymaster')
			document.info.action = from+act;
		else if(from=='bankmaster')
			document.info.action = from+act;
		else if(from=='materialmaster')
			document.info.action = from+act;
		else if(from=='warratemaster')
			document.info.action = from+act;
		else if(from=='saletermmaster')
			document.info.action = from+act;
		else if(from=='tolerancemaster')
			document.info.action = from+act;
		else if(from=='commodityexcess')
			document.info.action = from+act;
		else if(from=='vesselmaster')
			document.info.action = from+act;
		else if(from=='settlingagent')
			document.info.action = from+act;
		else if(from=='exchangemaster')
			document.info.action = from+act;
		else if(from=='baserate')
			document.info.action = from+"RatingEngine";
		else if(from=='currencymaster')
			document.info.action = from+act;
		else if(from=='extracover')
			document.info.action = from+act;
		else if(from=='modeoftransport')
			document.info.action = from+act;
		else if(from=='warrantymaster')
			document.info.action = from+act;
		else if(from == 'constantMaster')
			document.info.action = from+act;
		else if(from=='constantdetail')
			document.info.action = from+act;
		else if(from=='exclusionmaster')
			document.info.action = from+act;
		else if(from=='citymaster')
			document.info.action = from+act;
		else if(from=='clause')
			document.info.action = from+act;
		else if(from=='wsrcc')
			document.info.action = from+act;
		else if(from=='covermaster')
			document.info.action = from+act;
		else if(from=='editWar')
			document.info.action = from+act;
		else if(from=='editConveyance')
			document.info.action=from+act;
		else if(from=='error')
			document.info.action = from+act;
		else if(from=='area')
	        document.info.action = from+actn;
		else if(from=='motorbody')
			document.info.action = from+actn;
		else if(from=='exchangeupload'){
			document.info.action = "upload"+act;
			try{
				document.getElementById('reqFrom').value='';
			}catch(err){}
		}else if(from=='vesselupload'){
			try{
				document.getElementById('reqFrom').value='';
			}catch(err){}
			document.info.action = 'upload'+act;
		}
		else if(from=='executivemaster') {
			document.info.action = from+act;
		}
		document.info.submit();
}

		