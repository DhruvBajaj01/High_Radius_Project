import logo from './logo.svg';
import './App.css';
import Header from './components/Header';
import {Buttons} from './components/Buttons'
import { DataGridMUI } from './components/DataGridMUI';
import { Footer } from './components/Footer';
import { useState } from 'react';

function App() {
  const [refresh, setRefresh] = useState(false);
  const [checkedArray, setCheckedArray] = useState([]);
  const [isdeleteChecked, setIsDeleteChecked] = useState([false]);
  const [tableData, setTableData]=useState([])
  const [searchButton, setSearchButton] = useState(false);

  return (
    <div className="container">
      <Header />
      <Buttons  searchButton={searchButton} setSearchButton={setSearchButton} tableData={tableData} setTableData={setTableData} refresh={refresh} setRefresh={setRefresh} checkedArray={checkedArray} setCheckedArray={setCheckedArray} isdeleteChecked={isdeleteChecked} setIsDeleteChecked={setIsDeleteChecked}/>
      <DataGridMUI searchButton={searchButton} setSearchButton={setSearchButton} tableData={tableData} setTableData={setTableData} refresh={refresh} setRefresh={setRefresh} checkedArray={checkedArray} setCheckedArray={setCheckedArray} isdeleteChecked={isdeleteChecked} setIsDeleteChecked={setIsDeleteChecked}/>
      <Footer />
    </div>
  );
}

export default App;