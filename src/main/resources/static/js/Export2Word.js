function Export2WordR(element, filename = ''){
    var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
    var postHtml = "</body></html>";
    var html = preHtml+document.getElementById(element).innerHTML+postHtml;

    var blob = new Blob(['\ufeff', html], {
        type: 'application/msword'
    });

    // Specify link url
    var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);

    // Specify file name
    filename = filename?filename+'.doc':'document.doc';

    // Create download link element
    var downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if(navigator.msSaveOrOpenBlob ){
        navigator.msSaveOrOpenBlob(blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = url;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
    }

    document.body.removeChild(downloadLink);
}


function Export2ExcelR(element, filename = 'SheetJSTable.xlsx'){
    /* Callback invoked when the button is clicked */

    /* Create worksheet from HTML DOM TABLE */
    const table = document.getElementById(element);
    const wb = XLSX.utils.table_to_book(table);

    /* Export to file (start a download) */
    XLSX.writeFile(wb, filename);

}


function Export2PDF(id="exportContent" ,name="sample-file.pdf" , f='A4' ) {

    if (name.split(".")[name.split(".").length - 1] != ".pdf") {
        name += ".pdf";
    }
    let element = document.getElementById(id);
    let ish =  element.hidden === true
    if (  ish ) {
        element.hidden = false;

    }
    console.log(element);
    var opt = {
        filename: name,
        image: { type: 'jpeg', quality: 0.98 },
        jsPDF: { unit: 'in', format: f, orientation: 'landscape' },
        html2canvas:  { dpi :true, letterRendering: true, useCORS: true,     logging: true},
        margin: 0.51,
        pagebreak: {
            mode: ['avoid-all', 'css', 'legacy']
        }

    };
    html2pdf().from(element).set(opt).save();

    setTimeout(()=>{
        if (ish) {
            element.hidden = true;

        }
    } , 0)


}


function Export2Word(element, filename = '', f='a4'){
    Export2PDF(element,filename);
}

function Export2Excel(id="exportContent" ,name="sample-file.pdf" , f='a0' ){
    var doc = new jspdf.jsPDF('p', 'pt', f);
    let ishdn = document.getElementById(id).hidden;
    if (ishdn) {
        document.getElementById(id).hidden = false;
    }

    let idd = `#${id}`;
    console.log("id :",idd);
    console.log("table : " , document.querySelector(idd));
    doc.html(document.querySelector(idd), {
        callback: function (doc) {
            doc.save('MLB World Series Winners.pdf');
        },
        margin: [60, 60, 60, 60],
        x: 32,
        y: 32,
    }).then(function () {
        if (ishdn) {
            document.getElementById(id).hidden = true;
        }
    });
}
