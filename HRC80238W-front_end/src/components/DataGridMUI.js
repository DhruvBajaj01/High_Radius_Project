import React, {useState,useEffect} from 'react'
import {DataGrid} from '@mui/x-data-grid'
import { makeStyles } from '@material-ui/core'
import axios from 'axios';

// import { makeStyles } from '@mui/material'

//const [q, setQ] = useState("");

const useStyle = makeStyles(()=>({
  iconSeparator:{
    display: "none",
  }
}));

const columns = [
    {field: 'sl_no',headerName: 'Sl No', width: 70},
    {field: 'business_code',headerName: 'Business Code', width: 130},
    {field: 'cust_number',headerName: 'Customer Number', width: 130},
    {field: 'clear_date',headerName: 'Clear Date',width: 130},
    {field: 'doc_id',headerName: 'Document Id',width: 139},
    {field: 'buisness_year',headerName: 'Buisness Year',width: 130},
    {field: 'posting_date',headerName: 'Posting Date', width: 130},
    {field: 'document_create_date',headerName: 'Document Create Date',width: 200},
    {field: 'due_in_date',headerName: 'Due Date',width: 200},
    {field: 'invoice_currency',headerName: 'Invoice Currency',width: 200},
    {field: 'document_type',headerName: 'Document Type', width: 200},
    {field: 'posting_id',headerName: 'Posting Id',width: 200},
    {field: 'total_open_amount',headerName: 'Total Open Amount',width: 200},
    {field: 'baseline_create_date',headerName: 'Baseline Create Date',width: 200},
    {field: 'cust_payment_terms',headerName: 'Customer Payment Terms', width: 200},
    {field: 'invoice_id',headerName: 'Invoice Id',width: 200},
]

export const DataGridMUI = () => {
const [pageSize, setPageSize] = useState(5);
const [tableData, setTableData]=useState([])

const {iconSeparator} = useStyle();

// useEffect(()=> {
//     fetch("https://jsonplaceholder.typicode.com/posts")
//     .then((data) => data.json())
//     .then((data) => setTableData(data))
// })
const fetchData = () => {
  try {
    // const res = await fetch('http://localhost:8080/highradius/Fetch');
    // const data = await res.json();
    axios.get('http://localhost:8080/highradius/Fetch').then(response => {
      console.log(response.data)
      setTableData(response.data);
    })
    //setTableData(data);
    //console.log(data);
  } catch (error) {
    console.log(error);
  }
}
useEffect(()=> {
   fetchData();
},[]);

/*function search(rows) {
  return rows.filter((row) => row.cust_number.toLowerCase().indexOf(q) > -1);
}*/

const rowData = tableData.map((row) => {
  return {
    sl_no: row?.sl_no,
    business_code: row?.business_code,
    cust_number: row?.cust_number,
    clear_date: row?.clear_date,
    doc_id: row?.doc_id,
    buisness_year: row?.buisness_year,
    posting_date: row?.posting_date,
    document_create_date: row?.document_create_date,
    due_in_date: row?.due_in_date,
    invoice_currency: row?.invoice_currency,
    document_type: row?.document_type,
    posting_id: row?.posting_id,
    total_open_amount: row?.total_open_amount,
    baseline_create_date: row?.baseline_create_date,
    cust_payment_terms: row?.cust_payment_terms,
    invoice_id: row?.invoice_id,
  }
});

  return (
    <div style={{height: 365, width:'100%'}} className="grid">
        <DataGrid sx={[{color: 'whitesmoke', border:"none", '.MuiDataGrid-iconSeparator':{display:"none"}, '.css-i4bv87-MuiSvgIcon-root':{color:"white"}, '.css-11bfvty-MuiToolbar-root-MuiTablePagination-toolbar':{color: "white"}}]} 
            rows={rowData}
            columns={columns}
            // pageSize={10}
            // rowsPerPageOptions={[5, 10, 20]}
            pageSize={pageSize}
            onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
            rowsPerPageOptions={[5, 10, 20]}
            pagination
            checkboxSelection
            getRowId={(row) => row.sl_no}
            className={iconSeparator}
        />
    </div>
  )
}