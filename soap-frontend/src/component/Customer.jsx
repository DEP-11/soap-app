import {useState} from "react";
import {getCustomer} from "../service/customerService.js";

const Customer = () => {
    const [customerId, setCustomerId] = useState('');
    const [customer, setCustomer] = useState(null);
    const [error, setError] = useState(null);

    const handleInputChange = (e) => {
        setCustomerId(e.target.value);
    };

    const fetchCustomer = async (e) => {
        e.preventDefault();
        setError(null);
        try {
            const customerData = await getCustomer(customerId);
            setCustomer(customerData);
        } catch (error) {
            setError('Failed to fetch customer data');
        }
    };

    return (
        <div>
            <h1>Customer Information</h1>
            <form onSubmit={fetchCustomer}>
                <label htmlFor="customerId">Customer ID:</label>
                <input
                    type="number"
                    id="customerId"
                    value={customerId}
                    onChange={handleInputChange}
                />
                <button type="submit">Get Customer</button>
            </form>

            {error && <p>{error}</p>}

            {customer && (
                <div>
                    <h2>Customer Details</h2>
                    <p>ID: {customer.id}</p>
                    <p>Name: {customer.name}</p>
                    <p>Address: {customer.address}</p>
                    <p>NIC: {customer.nic}</p>
                    <p>Mobile No: {customer.mobileNo}</p>
                </div>
            )}
        </div>
    );
};

export default Customer;