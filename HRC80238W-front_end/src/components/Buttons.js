import React, { useEffect, useState } from 'react'
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import { Refresh } from '@mui/icons-material';
import FormControl, { useFormControl } from '@mui/material/FormControl';
import OutlinedInput from '@mui/material/OutlinedInput';
// import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import '../App.css';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
// import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';
import  Axios  from 'axios';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: "rgb(45,66,80, 0.1)",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));


export const Buttons = (props) => {
  const urlAdd = "http://localhost:8080/highradius/Add?";
  const urlEdit = "http://localhost:8080/highradius/Edit?";

  const [data, setData] = useState({
    business_code : "",
    cust_number : "",
    clear_date : "",
    business_year : "",
    doc_id : "",
    posting_date: "",
    document_create_date : "",
    due_in_date : "",
    invoice_currency : "",
    document_type : "",
    posting_id : "",
    total_open_amount : "",
    baseline_create_date : "",
    cust_payment_terms : "",
    invoice_id : ""

  })
  const buttonGroupStyle = [{
    color: "white",
    '&:hover': {
      color: '#fff',
      backgroundColor: '#14aff1',
      
    },
    padding: "0px 40px 0px",
    height: "40px",
    
  }
  ];

  const buttonStyles = [
    {
      color: "white",
      '&:hover': {
        color: '#fff',
        backgroundColor: '#14aff1',
      },
      padding: "0px 100px 0px",
      height: "40px",
    }
  ];
  const formStyle = [
    {
      color: "black",
      backgroundColor: "white",
      padding: "3px 0px -2px",
      borderRadius: "7px",
      height: "57px",
      width: "250px",
    }
  ];
  const searchformStyle = [
    {
      color: "black",
      backgroundColor: "white",
      padding: "-2px 0px -2px",
      borderRadius: "15px",
      height: "40px",
      width: "200px",
    }
  ];

  
  const [open, setOpen] = useState(false);
  const [deleteOpen, setDeleteOpen] = useState(false);
  const [editOpen, setEditOpen] = useState(false);
  const [searchOpen, setSearchOpen] = useState(false);
  const [isEnabled, setIsEnabled] = useState(true);
  const [valedit, setValEdit] = useState({
    sl_no: "",
    invoice_currency: "",
    cust_payment_terms: "",
     });
  const [deleteForm, setDeleteForm] = useState({ sl_no: []});
  const [searchForm, setSearchForm] = useState({
    cust_number: ""
  });

  const [advsearchForm, setAdvsearchForm] = useState({
    doc_id: "",
    invoice_id: "",
    cust_number: "",
    business_year: "",
  });
  const [predictForm, setPredictForm] = useState({
    business_code: "",
    cust_number: "",
    name_customer: "",
    clear_date: "",
    business_year: "",
    doc_id: "",
    posting_date: "",
    due_in_date: "",
    baseline_create_date: "",
    cust_payment_terms: "",
    converted_usd: "",
  });

  const handleSearchOpen = () => {
    setSearchOpen(true);
    props.setSearchButton(true);
  };

  const handleSearchClose = () => {
    setSearchOpen(false);
  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const deleteHandleClickOpen = () => {
    setDeleteOpen(true);
  };
  const deleteHandleClose = () => {
    setDeleteOpen(false);
  };

  const editHandleClickOpen = () => {
    setEditOpen(true);
  };
  const editHandleClose = () => {
    setEditOpen(false);
  };

  {/* -------------this is the code for Add functionality-------------- */}

  function submitAdd(e){
    e.preventDefault();
    Axios.get(urlAdd + topass)
    .then(res=>{
      console.log(res.data)
      window.alert("Data Added Successfully!");
      handleClose();
      if(res) { 
        setData({ business_code: '', 
                  cust_number: '',
                  clear_date: '',
                  business_year: '',
                  doc_id: '',
                  posting_date: '',
                  document_create_date: '',
                  due_in_date: '',
                  invoice_currency: '',
                  document_type: '',
                  posting_id: '',
                  total_open_amount: '',
                  baseline_create_date: '',
                  cust_payment_terms: '',
                  invoice_id: ''
      
      })
      }
    })
} 
let topass= "business_code="+ data.business_code+
"&cust_number="+ data.cust_number+
"&clear_date="+ data.clear_date+
"&buisness_year="+ data.business_year+
"&doc_id="+ data.doc_id+
"&posting_date="+ data.posting_date+
"&document_create_date="+ data.document_create_date+
"&due_in_date="+ data.due_in_date+
"&invoice_currency="+ data.invoice_currency+
"&document_type="+ data.document_type+
"&posting_id="+ data.posting_id+
"&total_open_amount="+ data.total_open_amount+
"&baseline_create_date="+ data.baseline_create_date+
"&cust_payment_terms="+ data.cust_payment_terms+
"&invoice_id="+ data.invoice_id;


  function handleAdd(e){
    const newdata = {...data}
    newdata[e.target.id] = e.target.value
    setData(newdata)
    console.log(newdata)
  }

  {/* ------------------------------------------------------------------------------- */}

  {/* -------------this is the code for Edit functionality-------------- */}

  const onEditChange = (e) => {
    setValEdit({ ...valedit, [e.target.name]: e.target.value});
    
  };

  let Stringg = 
  "sl_no=" + 
  valedit.sl_no +
  "&invoice_currency=" + 
  valedit.invoice_currency+ 
  "&cust_payment_terms=" + 
  valedit.cust_payment_terms ;

  function submitEdit(e){
    e.preventDefault();
    Axios.get(urlEdit + Stringg)
    .then(res=>{
    console.log(valedit);
    window.alert("Data Updated Successfully!");
    editHandleClose();
    doRefresh();
  })
}

{/* ------------------------------------------------------------------------------- */}

{/* -------------this is the code for Delete functionality-------------- */}

const onDeleteSubmit = async (e) => {
  var resp = "sl_no=" + deleteForm.sl_no;
  e.preventDefault();
  

  Axios.get('http://localhost:8080/highradius/Del?' + resp)
    .then(res => {
      console.log(res.data)
      window.alert("Deletion Successful!")
    })

  

  console.log(deleteForm, resp);
  deleteHandleClose();
  doRefresh();
}

{/* ------------------------------------------------------------------------------- */}

{/* -------------this is the code for Search Bar functionality-------------- */}

const onSearchChange = async (e) => {
    
  setSearchForm({ ...searchForm, [e.target.name]: e.target.value});

  var resp = "cust_number=" + searchForm.cust_number;

  try {
    const res = await fetch("http://localhost:8080/highradius/SearchId?" + resp);
    const data = await res.json();
    props.setTableData(data);
    console.log(data);
  } catch (error) {
    //console.log(error);
  };
  
};

{/* ------------------------------------------------------------------------------- */}



     {/* -------------this is the code for Refresh functionality-------------- */}

     function doRefresh(){
      //props.setSearchURL('http://localhost:8080/backend/fetching_showing_data');
      props.setRefresh(!props.refresh);
  
    }

    {/* ------------------------------------------------------------------------------- */}


  useEffect(()=>{
    if(props.checkedArray.length === 1){
      setIsEnabled(false);
      setValEdit({ ...valedit, sl_no: props.checkedArray[0] });
      setDeleteForm({ ...deleteForm, sl_no: props.checkedArray });
    }
    else if(props.checkedArray.length > 1){
    setIsEnabled(true);
    setValEdit({
      invoice_currency: "",
      cust_payment_terms: "",
      sl_no: ""
    });
    setDeleteForm({ ...deleteForm, sl_no: props.checkedArray });
    }
    else if(props.checkedArray.length < 1)
    {
      setIsEnabled(true);
      setValEdit({
        invoice_currency: "",
      cust_payment_terms: "",
      sl_no: ""
      });
      setDeleteForm({sl_no: []}); 
    }
  },[props.checkedArray.length])
  
  

  return (
    <div className='buttons'>
      <Stack direction="row" spacing={2} sx={[{'css-1ja9oug-MuiStack-root':{justifyContent: "center"}}]}>
        <ButtonGroup aria-label="outlined primary button group">
          
          <Button variant="outlined" size="large" sx={buttonGroupStyle}>Analytics View</Button>
          <Button variant="outlined" size="large" onClick={handleSearchOpen} sx={buttonGroupStyle}>Advance Search</Button>
          <Dialog open={searchOpen} onClose={handleSearchClose} fullWidth={true} maxWidth={'sm'}>
            <DialogTitle style={{ color: "white", backgroundColor: "#2d4250" }}>Advance Search</DialogTitle>
              <DialogContent style={{ color: "white", backgroundColor: "#2d4250" }}>
                <FormControl sx={[{ width: '25ch', padding: "2px 5px 2px"  }]}>
              {/* <Box sx={{ width: '100%' }}> */}
                  <Stack direction="row" spacing={3}>
                   
                  </Stack>
                  <Stack direction="row" spacing={3}>
                    
                  </Stack>
              {/* </Box> */}
              </FormControl>
            </DialogContent>
            <DialogActions style={{ backgroundColor: "#2d4250" }}>
            
            <Button variant="outlined" size="large" onClick={handleSearchClose} style={{ width: "50%" }}>Cancel</Button>
          </DialogActions>
        </Dialog>
          <Button onClick={doRefresh} variant="outlined" startIcon={<Refresh />} sx={[{ marginLeft: "7px", padding: "10px 0px 10px 7px", '&:hover': { color: '#fff', backgroundColor: '#14aff1', }, height: "40px", }]}></Button>
        </ButtonGroup>


        
        {/* <Box component="form" noValidate autoComplete="off"> */}
        <FormControl sx={[{ width: '25ch', padding: "2px 25px 7px" }]}>
          <TextField onChange={onSearchChange} type="text" placeholder="Search Customer ID" sx={searchformStyle} />
          
          </FormControl>
        {/* </Box> */}

        {/* -------------this is the Add functionality dialog box code-------------- */}

        <Button variant="outlined" size="large" sx={buttonStyles} onClick={handleClickOpen}> Add</Button>
        <Dialog open={open} onClose={handleClose} fullWidth={true} maxWidth={'lg'}>
          <DialogTitle style={{ color: "white", backgroundColor: "#2d4250" }}>Add</DialogTitle>
          <DialogContent style={{ backgroundColor: "#2d4250" }}>
            <FormControl sx={[{ width: '25ch', padding: "2px 5px 2px" }]}>
              <Box sx={{ width: '100%' }}>
                <Stack spacing={2}>
                  <Stack direction="row" spacing={1}>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="business_code" value={data.business_code} label="Business Code" placeholder="Business Code" sx={formStyle} autoFocus /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="cust_number" value={data.cust_number} label="Customer Number" placeholder="Customer Number" sx={formStyle} /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="clear_date" value={data.clear_date} label="Clear Date" placeholder="Clear Date" type='date' sx={formStyle} /></Item>
                    
                    <Item><TextField onChange={(e) => handleAdd(e)} id="business_year" value={data.business_year} label="Business Year" placeholder="Business Year" sx={formStyle} autoFocus /></Item>
                    
                  </Stack>
                  <Stack direction="row" spacing={1}>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="doc_id" value={data.doc_id} label="Document ID" placeholder="Document ID" sx={formStyle} /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="posting_date" value={data.posting_date} label="Posting Date" placeholder="Posting Date" type='date' sx={formStyle} /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="document_create_date" value={data.document_create_date} label="Document Create Date" type='date' sx={formStyle} /></Item>

                    <Item><TextField onChange={(e) => handleAdd(e)} id="due_in_date" value={data.due_in_date} label="Due Date" placeholder="Due Date" type='date' sx={formStyle} /></Item>

                  </Stack>
                  <Stack direction="row" spacing={1}>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="invoice_currency" value={data.invoice_currency} label="Invoice Currency" placeholder="Invoice Currency" sx={formStyle} autoFocus /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="document_type" value={data.document_type} label="Document Type" placeholder="Document type" sx={formStyle} /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="posting_id" value={data.posting_id} label="Posting ID" placeholder="Posting Id" sx={formStyle} /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="total_open_amount" value={data.total_open_amount} label="Total Open Amount" placeholder="Total open amount" sx={formStyle} /></Item>
                  </Stack>
                  <Stack direction="row" spacing={1}>
                  <Item><TextField onChange={(e) => handleAdd(e)} id="baseline_create_date" value={data.baseline_create_date} label="Baseline Create Date" placeholder="Baseline Create Date" type='date' sx={formStyle} /></Item>

                    <Item><TextField onChange={(e) => handleAdd(e)} id="cust_payment_terms" value={data.cust_payment_terms} label="Customer Payment Terms" placeholder="Customer Payment Terms" sx={formStyle} autoFocus /></Item>
                    <Item><TextField onChange={(e) => handleAdd(e)} id="invoice_id" value={data.invoice_id} label="Invoice ID" placeholder="Invoice id" sx={formStyle} /></Item>
                    
                  </Stack>
                </Stack>
              </Box>
            </FormControl>
          </DialogContent>
          <DialogActions style={{ backgroundColor: "#2d4250" }}>
            <Button variant="outlined" size="large" onClick={(e) => submitAdd(e)} style={{ width: "50%" }}>Add</Button>
            <Button variant="outlined" size="large" onClick={handleClose} style={{ width: "50%" }}>Cancel</Button>
          </DialogActions>
        </Dialog>

        {/* -------------this is the Edit functionality dialog box code-------------- */}

        
        <Button variant="outlined" size="large" sx={buttonStyles} style={{ color: "white" }} onClick={editHandleClickOpen} disabled={isEnabled}>Edit</Button>
        
        <Dialog open={editOpen} onClose={editHandleClose} fullWidth={true} maxWidth={'sm'}>
          <DialogTitle style={{ color: "white", backgroundColor: "#2d4250" }}>Edit</DialogTitle>
          <DialogContent style={{ color: "white", backgroundColor: "#2d4250" }}>
            <FormControl sx={[{ width: '25ch', padding: "2px 5px 2px" }]}>
              {/* <Box sx={{ width: '100%' }}> */}
                <Stack direction="row" spacing={1}>
                  <Item><TextField label="Invoice Currency" name="invoice_currency" onChange={onEditChange} placeholder="Invoice Currency" sx={formStyle} autoFocus /></Item>
                  <Item><TextField label="Customer Payment Terms" name="cust_payment_terms" onChange={onEditChange} placeholder="Customer Payment Terms" sx={formStyle} /></Item>
                </Stack>
              {/* </Box> */}
            </FormControl>
          </DialogContent>
          <DialogActions style={{ backgroundColor: "#2d4250" }}>
            <Button variant="outlined" size="large" onClick={submitEdit} style={{ width: "50%" }}>Edit</Button>
            <Button variant="outlined" size="large" onClick={editHandleClose} style={{ width: "50%" }}>Cancel</Button>
          </DialogActions>
        </Dialog>

        {/* -------------this is the Delete functionality dialog box code-------------- */}

        <Button variant="outlined" size="large" sx={buttonStyles} onClick={deleteHandleClickOpen} >Delete</Button>
        <Dialog open={deleteOpen} onClose={deleteHandleClose} fullWidth={true} maxWidth={'sm'}>
          <DialogTitle style={{ color: "white", backgroundColor: "#2d4250" }}> Delete Records ? </DialogTitle>
          <DialogContent style={{ color: "white", backgroundColor: "#2d4250" }}>
            Are you Sure you want to delete these record[s]?
          </DialogContent>
          <DialogActions style={{ backgroundColor: "#2d4250" }}>
            <Button variant="outlined" size="large" onClick={deleteHandleClose} style={{ width: "50%" }}>Cancel</Button>
            <Button variant="outlined" size="large" onClick={onDeleteSubmit} style={{ width: "50%" }}>Delete</Button>
          </DialogActions>
        </Dialog>
      </Stack>
    </div>
  )
}