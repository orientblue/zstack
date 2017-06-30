package org.zstack.sdk;

import java.util.HashMap;

public class SourceClassMap {
    final static HashMap<String, String> srcToDstMapping = new HashMap() {
        {
			put("org.zstack.appliancevm.ApplianceVmInventory", "org.zstack.sdk.ApplianceVmInventory");
			put("org.zstack.billing.DataVolumeSpending", "org.zstack.sdk.DataVolumeSpending");
			put("org.zstack.billing.DataVolumeSpendingInventory", "org.zstack.sdk.DataVolumeSpendingInventory");
			put("org.zstack.billing.PriceInventory", "org.zstack.sdk.PriceInventory");
			put("org.zstack.billing.RootVolumeSpending", "org.zstack.sdk.RootVolumeSpending");
			put("org.zstack.billing.RootVolumeSpendingInventory", "org.zstack.sdk.RootVolumeSpendingInventory");
			put("org.zstack.billing.SnapShotSpendingInventory", "org.zstack.sdk.SnapShotSpendingInventory");
			put("org.zstack.billing.SnapshotSpending", "org.zstack.sdk.SnapshotSpending");
			put("org.zstack.billing.Spending", "org.zstack.sdk.Spending");
			put("org.zstack.billing.SpendingDetails", "org.zstack.sdk.SpendingDetails");
			put("org.zstack.billing.VmSpending", "org.zstack.sdk.VmSpending");
			put("org.zstack.billing.VmSpendingDetails", "org.zstack.sdk.VmSpendingDetails");
			put("org.zstack.core.config.GlobalConfigInventory", "org.zstack.sdk.GlobalConfigInventory");
			put("org.zstack.core.gc.GarbageCollectorInventory", "org.zstack.sdk.GarbageCollectorInventory");
			put("org.zstack.core.notification.NotificationInventory", "org.zstack.sdk.NotificationInventory");
			put("org.zstack.core.notification.NotificationSubscriptionInventory", "org.zstack.sdk.NotificationSubscriptionInventory");
			put("org.zstack.header.aliyun.AliyunException", "org.zstack.sdk.AliyunException");
			put("org.zstack.header.aliyun.AliyunOssException", "org.zstack.sdk.AliyunOssException");
			put("org.zstack.header.aliyun.ecs.EcsInstanceInventory", "org.zstack.sdk.EcsInstanceInventory");
			put("org.zstack.header.aliyun.image.EcsImageInventory", "org.zstack.sdk.EcsImageInventory");
			put("org.zstack.header.aliyun.network.HybridConnectionType", "org.zstack.sdk.HybridConnectionType");
			put("org.zstack.header.aliyun.network.connection.ConnectionAccessPointInventory", "org.zstack.sdk.ConnectionAccessPointInventory");
			put("org.zstack.header.aliyun.network.connection.ConnectionRelationShipInventory", "org.zstack.sdk.ConnectionRelationShipInventory");
			put("org.zstack.header.aliyun.network.connection.ConnectionRelationShipProperty", "org.zstack.sdk.ConnectionRelationShipProperty");
			put("org.zstack.header.aliyun.network.connection.VirtualBorderRouterInventory", "org.zstack.sdk.VirtualBorderRouterInventory");
			put("org.zstack.header.aliyun.network.connection.VirtualRouterInterfaceInventory", "org.zstack.sdk.VirtualRouterInterfaceInventory");
			put("org.zstack.header.aliyun.network.group.EcsSecurityGroupInventory", "org.zstack.sdk.EcsSecurityGroupInventory");
			put("org.zstack.header.aliyun.network.group.EcsSecurityGroupRuleInventory", "org.zstack.sdk.EcsSecurityGroupRuleInventory");
			put("org.zstack.header.aliyun.network.vpc.EcsVSwitchInventory", "org.zstack.sdk.EcsVSwitchInventory");
			put("org.zstack.header.aliyun.network.vpc.EcsVpcInventory", "org.zstack.sdk.EcsVpcInventory");
			put("org.zstack.header.aliyun.network.vrouter.VRouterType", "org.zstack.sdk.VRouterType");
			put("org.zstack.header.aliyun.network.vrouter.VpcVirtualRouteEntryInventory", "org.zstack.sdk.VpcVirtualRouteEntryInventory");
			put("org.zstack.header.aliyun.network.vrouter.VpcVirtualRouterInventory", "org.zstack.sdk.VpcVirtualRouterInventory");
			put("org.zstack.header.aliyun.oss.OssBucketInventory", "org.zstack.sdk.OssBucketInventory");
			put("org.zstack.header.aliyun.oss.OssBucketProperty", "org.zstack.sdk.OssBucketProperty");
			put("org.zstack.header.baremetal.BaremetalException", "org.zstack.sdk.BaremetalException");
			put("org.zstack.header.baremetal.host.BaremetalHostCfgInventory", "org.zstack.sdk.BaremetalHostCfgInventory");
			put("org.zstack.header.baremetal.host.BaremetalHostNicCfgStruct", "org.zstack.sdk.BaremetalHostNicCfgStruct");
			put("org.zstack.header.baremetal.power.BaremetalChassisInventory", "org.zstack.sdk.BaremetalChassisInventory");
			put("org.zstack.header.baremetal.pxeserver.BaremetalPxeServerInventory", "org.zstack.sdk.BaremetalPxeServerInventory");
			put("org.zstack.header.cluster.ClusterInventory", "org.zstack.sdk.ClusterInventory");
			put("org.zstack.header.configuration.DiskOfferingInventory", "org.zstack.sdk.DiskOfferingInventory");
			put("org.zstack.header.configuration.InstanceOfferingInventory", "org.zstack.sdk.InstanceOfferingInventory");
			put("org.zstack.header.console.ConsoleInventory", "org.zstack.sdk.ConsoleInventory");
			put("org.zstack.header.console.ConsoleProxyAgentInventory", "org.zstack.sdk.ConsoleProxyAgentInventory");
			put("org.zstack.header.core.progress.TaskProgressInventory", "org.zstack.sdk.TaskProgressInventory");
			put("org.zstack.header.core.scheduler.SchedulerJobInventory", "org.zstack.sdk.SchedulerJobInventory");
			put("org.zstack.header.core.scheduler.SchedulerJobSchedulerTriggerInventory", "org.zstack.sdk.SchedulerJobSchedulerTriggerInventory");
			put("org.zstack.header.core.scheduler.SchedulerTriggerInventory", "org.zstack.sdk.SchedulerTriggerInventory");
			put("org.zstack.header.core.webhooks.WebhookInventory", "org.zstack.sdk.WebhookInventory");
			put("org.zstack.header.datacenter.DataCenterInventory", "org.zstack.sdk.DataCenterInventory");
			put("org.zstack.header.datacenter.DataCenterProperty", "org.zstack.sdk.DataCenterProperty");
			put("org.zstack.header.errorcode.ErrorCode", "org.zstack.sdk.ErrorCode");
			put("org.zstack.header.errorcode.ErrorCodeList", "org.zstack.sdk.ErrorCodeList");
			put("org.zstack.header.host.HostInventory", "org.zstack.sdk.HostInventory");
			put("org.zstack.header.hybrid.network.eip.HybridEipAddressInventory", "org.zstack.sdk.HybridEipAddressInventory");
			put("org.zstack.header.hybrid.network.vpn.VpcUserVpnGatewayInventory", "org.zstack.sdk.VpcUserVpnGatewayInventory");
			put("org.zstack.header.hybrid.network.vpn.VpcVpnConnectionInventory", "org.zstack.sdk.VpcVpnConnectionInventory");
			put("org.zstack.header.hybrid.network.vpn.VpcVpnGatewayInventory", "org.zstack.sdk.VpcVpnGatewayInventory");
			put("org.zstack.header.hybrid.network.vpn.VpcVpnIkeConfigInventory", "org.zstack.sdk.VpcVpnIkeConfigInventory");
			put("org.zstack.header.hybrid.network.vpn.VpcVpnIkeConfigStruct", "org.zstack.sdk.VpcVpnIkeConfigStruct");
			put("org.zstack.header.hybrid.network.vpn.VpcVpnIpSecConfigInventory", "org.zstack.sdk.VpcVpnIpSecConfigInventory");
			put("org.zstack.header.hybrid.network.vpn.VpcVpnIpSecConfigStruct", "org.zstack.sdk.VpcVpnIpSecConfigStruct");
			put("org.zstack.header.identity.AccountConstant$StatementEffect", "org.zstack.sdk.PolicyStatementEffect");
			put("org.zstack.header.identity.AccountInventory", "org.zstack.sdk.AccountInventory");
			put("org.zstack.header.identity.AccountResourceRefInventory", "org.zstack.sdk.AccountResourceRefInventory");
			put("org.zstack.header.identity.PolicyInventory", "org.zstack.sdk.PolicyInventory");
			put("org.zstack.header.identity.PolicyInventory$Statement", "org.zstack.sdk.PolicyStatement");
			put("org.zstack.header.identity.Quota$QuotaUsage", "org.zstack.sdk.QuotaUsage");
			put("org.zstack.header.identity.QuotaInventory", "org.zstack.sdk.QuotaInventory");
			put("org.zstack.header.identity.SessionInventory", "org.zstack.sdk.SessionInventory");
			put("org.zstack.header.identity.SharedResourceInventory", "org.zstack.sdk.SharedResourceInventory");
			put("org.zstack.header.identity.UserGroupInventory", "org.zstack.sdk.UserGroupInventory");
			put("org.zstack.header.identity.UserInventory", "org.zstack.sdk.UserInventory");
			put("org.zstack.header.identityzone.IdentityZoneInventory", "org.zstack.sdk.IdentityZoneInventory");
			put("org.zstack.header.identityzone.IdentityZoneProperty", "org.zstack.sdk.IdentityZoneProperty");
			put("org.zstack.header.image.APICreateRootVolumeTemplateFromVolumeSnapshotEvent$Failure", "org.zstack.sdk.CreateRootVolumeTemplateFromVolumeSnapshotFailure");
			put("org.zstack.header.image.ImageBackupStorageRefInventory", "org.zstack.sdk.ImageBackupStorageRefInventory");
			put("org.zstack.header.image.ImageInventory", "org.zstack.sdk.ImageInventory");
			put("org.zstack.header.managementnode.ManagementNodeInventory", "org.zstack.sdk.ManagementNodeInventory");
			put("org.zstack.header.network.l2.L2NetworkInventory", "org.zstack.sdk.L2NetworkInventory");
			put("org.zstack.header.network.l2.L2VlanNetworkInventory", "org.zstack.sdk.L2VlanNetworkInventory");
			put("org.zstack.header.network.l3.FreeIpInventory", "org.zstack.sdk.FreeIpInventory");
			put("org.zstack.header.network.l3.IpRangeInventory", "org.zstack.sdk.IpRangeInventory");
			put("org.zstack.header.network.l3.L3NetworkInventory", "org.zstack.sdk.L3NetworkInventory");
			put("org.zstack.header.network.service.NetworkServiceL3NetworkRefInventory", "org.zstack.sdk.NetworkServiceL3NetworkRefInventory");
			put("org.zstack.header.network.service.NetworkServiceProviderInventory", "org.zstack.sdk.NetworkServiceProviderInventory");
			put("org.zstack.header.simulator.SimulatorHostInventory", "org.zstack.sdk.SimulatorHostInventory");
			put("org.zstack.header.storage.backup.BackupStorageInventory", "org.zstack.sdk.BackupStorageInventory");
			put("org.zstack.header.storage.primary.PrimaryStorageInventory", "org.zstack.sdk.PrimaryStorageInventory");
			put("org.zstack.header.storage.snapshot.VolumeSnapshotBackupStorageRefInventory", "org.zstack.sdk.VolumeSnapshotBackupStorageRefInventory");
			put("org.zstack.header.storage.snapshot.VolumeSnapshotInventory", "org.zstack.sdk.VolumeSnapshotInventory");
			put("org.zstack.header.storage.snapshot.VolumeSnapshotTree$SnapshotLeafInventory", "org.zstack.sdk.SnapshotLeafInventory");
			put("org.zstack.header.storage.snapshot.VolumeSnapshotTreeInventory", "org.zstack.sdk.VolumeSnapshotTreeInventory");
			put("org.zstack.header.tag.SystemTagInventory", "org.zstack.sdk.SystemTagInventory");
			put("org.zstack.header.tag.TagInventory", "org.zstack.sdk.TagInventory");
			put("org.zstack.header.tag.UserTagInventory", "org.zstack.sdk.UserTagInventory");
			put("org.zstack.header.vm.CloneVmInstanceInventory", "org.zstack.sdk.CloneVmInstanceInventory");
			put("org.zstack.header.vm.CloneVmInstanceResults", "org.zstack.sdk.CloneVmInstanceResults");
			put("org.zstack.header.vm.VmInstanceInventory", "org.zstack.sdk.VmInstanceInventory");
			put("org.zstack.header.vm.VmNicInventory", "org.zstack.sdk.VmNicInventory");
			put("org.zstack.header.vo.ResourceInventory", "org.zstack.sdk.ResourceInventory");
			put("org.zstack.header.volume.APIGetVolumeFormatReply$VolumeFormatReplyStruct", "org.zstack.sdk.VolumeFormatReplyStruct");
			put("org.zstack.header.volume.VolumeInventory", "org.zstack.sdk.VolumeInventory");
			put("org.zstack.header.zone.ZoneInventory", "org.zstack.sdk.ZoneInventory");
			put("org.zstack.hybrid.account.HybridAccountInventory", "org.zstack.sdk.HybridAccountInventory");
			put("org.zstack.hybrid.core.HybridType", "org.zstack.sdk.HybridType");
			put("org.zstack.ipsec.IPsecConnectionInventory", "org.zstack.sdk.IPsecConnectionInventory");
			put("org.zstack.ipsec.IPsecPeerCidrInventory", "org.zstack.sdk.IPsecPeerCidrInventory");
			put("org.zstack.kvm.APIKvmRunShellEvent$ShellResult", "org.zstack.sdk.ShellResult");
			put("org.zstack.kvm.KVMHostInventory", "org.zstack.sdk.KVMHostInventory");
			put("org.zstack.kvm.KVMIsoTO", "org.zstack.sdk.KVMIsoTO");
			put("org.zstack.ldap.LdapAccountRefInventory", "org.zstack.sdk.LdapAccountRefInventory");
			put("org.zstack.ldap.LdapServerInventory", "org.zstack.sdk.LdapServerInventory");
			put("org.zstack.license.LicenseInventory", "org.zstack.sdk.LicenseInventory");
			put("org.zstack.mevoco.ShareableVolumeVmInstanceRefInventory", "org.zstack.sdk.ShareableVolumeVmInstanceRefInventory");
			put("org.zstack.network.l2.vxlan.vtep.VtepInventory", "org.zstack.sdk.VtepInventory");
			put("org.zstack.network.l2.vxlan.vxlanNetwork.L2VxlanNetworkInventory", "org.zstack.sdk.L2VxlanNetworkInventory");
			put("org.zstack.network.l2.vxlan.vxlanNetworkPool.L2VxlanNetworkPoolInventory", "org.zstack.sdk.L2VxlanNetworkPoolInventory");
			put("org.zstack.network.l2.vxlan.vxlanNetworkPool.VniRangeInventory", "org.zstack.sdk.VniRangeInventory");
			put("org.zstack.network.securitygroup.SecurityGroupIngressRuleTO", "org.zstack.sdk.SecurityGroupIngressRuleTO");
			put("org.zstack.network.securitygroup.SecurityGroupInventory", "org.zstack.sdk.SecurityGroupInventory");
			put("org.zstack.network.securitygroup.SecurityGroupRuleInventory", "org.zstack.sdk.SecurityGroupRuleInventory");
			put("org.zstack.network.securitygroup.VmNicSecurityGroupRefInventory", "org.zstack.sdk.VmNicSecurityGroupRefInventory");
			put("org.zstack.network.service.eip.EipInventory", "org.zstack.sdk.EipInventory");
			put("org.zstack.network.service.lb.LoadBalancerInventory", "org.zstack.sdk.LoadBalancerInventory");
			put("org.zstack.network.service.lb.LoadBalancerListenerInventory", "org.zstack.sdk.LoadBalancerListenerInventory");
			put("org.zstack.network.service.lb.LoadBalancerListenerVmNicRefInventory", "org.zstack.sdk.LoadBalancerListenerVmNicRefInventory");
			put("org.zstack.network.service.portforwarding.PortForwardingRuleInventory", "org.zstack.sdk.PortForwardingRuleInventory");
			put("org.zstack.network.service.vip.VipInventory", "org.zstack.sdk.VipInventory");
			put("org.zstack.network.service.virtualrouter.VirtualRouterOfferingInventory", "org.zstack.sdk.VirtualRouterOfferingInventory");
			put("org.zstack.network.service.virtualrouter.VirtualRouterVmInventory", "org.zstack.sdk.VirtualRouterVmInventory");
			put("org.zstack.storage.backup.imagestore.ImageStoreBackupStorageInventory", "org.zstack.sdk.ImageStoreBackupStorageInventory");
			put("org.zstack.storage.backup.sftp.SftpBackupStorageInventory", "org.zstack.sdk.SftpBackupStorageInventory");
			put("org.zstack.storage.ceph.backup.CephBackupStorageInventory", "org.zstack.sdk.CephBackupStorageInventory");
			put("org.zstack.storage.ceph.backup.CephBackupStorageMonInventory", "org.zstack.sdk.CephBackupStorageMonInventory");
			put("org.zstack.storage.ceph.primary.CephPrimaryStorageInventory", "org.zstack.sdk.CephPrimaryStorageInventory");
			put("org.zstack.storage.ceph.primary.CephPrimaryStorageMonInventory", "org.zstack.sdk.CephPrimaryStorageMonInventory");
			put("org.zstack.storage.ceph.primary.CephPrimaryStoragePoolInventory", "org.zstack.sdk.CephPrimaryStoragePoolInventory");
			put("org.zstack.storage.fusionstor.backup.FusionstorBackupStorageInventory", "org.zstack.sdk.FusionstorBackupStorageInventory");
			put("org.zstack.storage.fusionstor.backup.FusionstorBackupStorageMonInventory", "org.zstack.sdk.FusionstorBackupStorageMonInventory");
			put("org.zstack.storage.fusionstor.primary.FusionstorPrimaryStorageInventory", "org.zstack.sdk.FusionstorPrimaryStorageInventory");
			put("org.zstack.storage.fusionstor.primary.FusionstorPrimaryStorageMonInventory", "org.zstack.sdk.FusionstorPrimaryStorageMonInventory");
			put("org.zstack.storage.primary.local.APIGetLocalStorageHostDiskCapacityReply$HostDiskCapacity", "org.zstack.sdk.HostDiskCapacity");
			put("org.zstack.storage.primary.local.LocalStorageResourceRefInventory", "org.zstack.sdk.LocalStorageResourceRefInventory");
			put("org.zstack.vmware.ESXHostInventory", "org.zstack.sdk.ESXHostInventory");
			put("org.zstack.vmware.VCenterBackupStorageInventory", "org.zstack.sdk.VCenterBackupStorageInventory");
			put("org.zstack.vmware.VCenterClusterInventory", "org.zstack.sdk.VCenterClusterInventory");
			put("org.zstack.vmware.VCenterDatacenterInventory", "org.zstack.sdk.VCenterDatacenterInventory");
			put("org.zstack.vmware.VCenterInventory", "org.zstack.sdk.VCenterInventory");
			put("org.zstack.vmware.VCenterPrimaryStorageInventory", "org.zstack.sdk.VCenterPrimaryStorageInventory");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$1", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$2", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$3", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$4", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$5", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$6", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$7", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$8", "org.zstack.sdk.");
			put("org.zstack.vrouterRoute.VRouterRouteEntryAO", "org.zstack.sdk.VRouterRouteEntryAO");
			put("org.zstack.vrouterRoute.VRouterRouteEntryInventory", "org.zstack.sdk.VRouterRouteEntryInventory");
			put("org.zstack.vrouterRoute.VRouterRouteTableInventory", "org.zstack.sdk.VRouterRouteTableInventory");
			put("org.zstack.vrouterRoute.VirtualRouterVRouterRouteTableRefInventory", "org.zstack.sdk.VirtualRouterVRouterRouteTableRefInventory");
        }
    };

    final static HashMap<String, String> dstToSrcMapping = new HashMap() {
        {
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$1");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$2");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$3");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$4");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$5");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$6");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$7");
			put("org.zstack.sdk.", "org.zstack.vrouterRoute.APIGetVRouterRouteTableReply$8");
			put("org.zstack.sdk.AccountInventory", "org.zstack.header.identity.AccountInventory");
			put("org.zstack.sdk.AccountResourceRefInventory", "org.zstack.header.identity.AccountResourceRefInventory");
			put("org.zstack.sdk.AliyunException", "org.zstack.header.aliyun.AliyunException");
			put("org.zstack.sdk.AliyunOssException", "org.zstack.header.aliyun.AliyunOssException");
			put("org.zstack.sdk.ApplianceVmInventory", "org.zstack.appliancevm.ApplianceVmInventory");
			put("org.zstack.sdk.BackupStorageInventory", "org.zstack.header.storage.backup.BackupStorageInventory");
			put("org.zstack.sdk.BaremetalChassisInventory", "org.zstack.header.baremetal.power.BaremetalChassisInventory");
			put("org.zstack.sdk.BaremetalException", "org.zstack.header.baremetal.BaremetalException");
			put("org.zstack.sdk.BaremetalHostCfgInventory", "org.zstack.header.baremetal.host.BaremetalHostCfgInventory");
			put("org.zstack.sdk.BaremetalHostNicCfgStruct", "org.zstack.header.baremetal.host.BaremetalHostNicCfgStruct");
			put("org.zstack.sdk.BaremetalPxeServerInventory", "org.zstack.header.baremetal.pxeserver.BaremetalPxeServerInventory");
			put("org.zstack.sdk.CephBackupStorageInventory", "org.zstack.storage.ceph.backup.CephBackupStorageInventory");
			put("org.zstack.sdk.CephBackupStorageMonInventory", "org.zstack.storage.ceph.backup.CephBackupStorageMonInventory");
			put("org.zstack.sdk.CephPrimaryStorageInventory", "org.zstack.storage.ceph.primary.CephPrimaryStorageInventory");
			put("org.zstack.sdk.CephPrimaryStorageMonInventory", "org.zstack.storage.ceph.primary.CephPrimaryStorageMonInventory");
			put("org.zstack.sdk.CephPrimaryStoragePoolInventory", "org.zstack.storage.ceph.primary.CephPrimaryStoragePoolInventory");
			put("org.zstack.sdk.CloneVmInstanceInventory", "org.zstack.header.vm.CloneVmInstanceInventory");
			put("org.zstack.sdk.CloneVmInstanceResults", "org.zstack.header.vm.CloneVmInstanceResults");
			put("org.zstack.sdk.ClusterInventory", "org.zstack.header.cluster.ClusterInventory");
			put("org.zstack.sdk.ConnectionAccessPointInventory", "org.zstack.header.aliyun.network.connection.ConnectionAccessPointInventory");
			put("org.zstack.sdk.ConnectionRelationShipInventory", "org.zstack.header.aliyun.network.connection.ConnectionRelationShipInventory");
			put("org.zstack.sdk.ConnectionRelationShipProperty", "org.zstack.header.aliyun.network.connection.ConnectionRelationShipProperty");
			put("org.zstack.sdk.ConsoleInventory", "org.zstack.header.console.ConsoleInventory");
			put("org.zstack.sdk.ConsoleProxyAgentInventory", "org.zstack.header.console.ConsoleProxyAgentInventory");
			put("org.zstack.sdk.CreateRootVolumeTemplateFromVolumeSnapshotFailure", "org.zstack.header.image.APICreateRootVolumeTemplateFromVolumeSnapshotEvent$Failure");
			put("org.zstack.sdk.DataCenterInventory", "org.zstack.header.datacenter.DataCenterInventory");
			put("org.zstack.sdk.DataCenterProperty", "org.zstack.header.datacenter.DataCenterProperty");
			put("org.zstack.sdk.DataVolumeSpending", "org.zstack.billing.DataVolumeSpending");
			put("org.zstack.sdk.DataVolumeSpendingInventory", "org.zstack.billing.DataVolumeSpendingInventory");
			put("org.zstack.sdk.DiskOfferingInventory", "org.zstack.header.configuration.DiskOfferingInventory");
			put("org.zstack.sdk.ESXHostInventory", "org.zstack.vmware.ESXHostInventory");
			put("org.zstack.sdk.EcsImageInventory", "org.zstack.header.aliyun.image.EcsImageInventory");
			put("org.zstack.sdk.EcsInstanceInventory", "org.zstack.header.aliyun.ecs.EcsInstanceInventory");
			put("org.zstack.sdk.EcsSecurityGroupInventory", "org.zstack.header.aliyun.network.group.EcsSecurityGroupInventory");
			put("org.zstack.sdk.EcsSecurityGroupRuleInventory", "org.zstack.header.aliyun.network.group.EcsSecurityGroupRuleInventory");
			put("org.zstack.sdk.EcsVSwitchInventory", "org.zstack.header.aliyun.network.vpc.EcsVSwitchInventory");
			put("org.zstack.sdk.EcsVpcInventory", "org.zstack.header.aliyun.network.vpc.EcsVpcInventory");
			put("org.zstack.sdk.EipInventory", "org.zstack.network.service.eip.EipInventory");
			put("org.zstack.sdk.ErrorCode", "org.zstack.header.errorcode.ErrorCode");
			put("org.zstack.sdk.ErrorCodeList", "org.zstack.header.errorcode.ErrorCodeList");
			put("org.zstack.sdk.FreeIpInventory", "org.zstack.header.network.l3.FreeIpInventory");
			put("org.zstack.sdk.FusionstorBackupStorageInventory", "org.zstack.storage.fusionstor.backup.FusionstorBackupStorageInventory");
			put("org.zstack.sdk.FusionstorBackupStorageMonInventory", "org.zstack.storage.fusionstor.backup.FusionstorBackupStorageMonInventory");
			put("org.zstack.sdk.FusionstorPrimaryStorageInventory", "org.zstack.storage.fusionstor.primary.FusionstorPrimaryStorageInventory");
			put("org.zstack.sdk.FusionstorPrimaryStorageMonInventory", "org.zstack.storage.fusionstor.primary.FusionstorPrimaryStorageMonInventory");
			put("org.zstack.sdk.GarbageCollectorInventory", "org.zstack.core.gc.GarbageCollectorInventory");
			put("org.zstack.sdk.GlobalConfigInventory", "org.zstack.core.config.GlobalConfigInventory");
			put("org.zstack.sdk.HostDiskCapacity", "org.zstack.storage.primary.local.APIGetLocalStorageHostDiskCapacityReply$HostDiskCapacity");
			put("org.zstack.sdk.HostInventory", "org.zstack.header.host.HostInventory");
			put("org.zstack.sdk.HybridAccountInventory", "org.zstack.hybrid.account.HybridAccountInventory");
			put("org.zstack.sdk.HybridConnectionType", "org.zstack.header.aliyun.network.HybridConnectionType");
			put("org.zstack.sdk.HybridEipAddressInventory", "org.zstack.header.hybrid.network.eip.HybridEipAddressInventory");
			put("org.zstack.sdk.HybridType", "org.zstack.hybrid.core.HybridType");
			put("org.zstack.sdk.IPsecConnectionInventory", "org.zstack.ipsec.IPsecConnectionInventory");
			put("org.zstack.sdk.IPsecPeerCidrInventory", "org.zstack.ipsec.IPsecPeerCidrInventory");
			put("org.zstack.sdk.IdentityZoneInventory", "org.zstack.header.identityzone.IdentityZoneInventory");
			put("org.zstack.sdk.IdentityZoneProperty", "org.zstack.header.identityzone.IdentityZoneProperty");
			put("org.zstack.sdk.ImageBackupStorageRefInventory", "org.zstack.header.image.ImageBackupStorageRefInventory");
			put("org.zstack.sdk.ImageInventory", "org.zstack.header.image.ImageInventory");
			put("org.zstack.sdk.ImageStoreBackupStorageInventory", "org.zstack.storage.backup.imagestore.ImageStoreBackupStorageInventory");
			put("org.zstack.sdk.InstanceOfferingInventory", "org.zstack.header.configuration.InstanceOfferingInventory");
			put("org.zstack.sdk.IpRangeInventory", "org.zstack.header.network.l3.IpRangeInventory");
			put("org.zstack.sdk.KVMHostInventory", "org.zstack.kvm.KVMHostInventory");
			put("org.zstack.sdk.KVMIsoTO", "org.zstack.kvm.KVMIsoTO");
			put("org.zstack.sdk.L2NetworkInventory", "org.zstack.header.network.l2.L2NetworkInventory");
			put("org.zstack.sdk.L2VlanNetworkInventory", "org.zstack.header.network.l2.L2VlanNetworkInventory");
			put("org.zstack.sdk.L2VxlanNetworkInventory", "org.zstack.network.l2.vxlan.vxlanNetwork.L2VxlanNetworkInventory");
			put("org.zstack.sdk.L2VxlanNetworkPoolInventory", "org.zstack.network.l2.vxlan.vxlanNetworkPool.L2VxlanNetworkPoolInventory");
			put("org.zstack.sdk.L3NetworkInventory", "org.zstack.header.network.l3.L3NetworkInventory");
			put("org.zstack.sdk.LdapAccountRefInventory", "org.zstack.ldap.LdapAccountRefInventory");
			put("org.zstack.sdk.LdapServerInventory", "org.zstack.ldap.LdapServerInventory");
			put("org.zstack.sdk.LicenseInventory", "org.zstack.license.LicenseInventory");
			put("org.zstack.sdk.LoadBalancerInventory", "org.zstack.network.service.lb.LoadBalancerInventory");
			put("org.zstack.sdk.LoadBalancerListenerInventory", "org.zstack.network.service.lb.LoadBalancerListenerInventory");
			put("org.zstack.sdk.LoadBalancerListenerVmNicRefInventory", "org.zstack.network.service.lb.LoadBalancerListenerVmNicRefInventory");
			put("org.zstack.sdk.LocalStorageResourceRefInventory", "org.zstack.storage.primary.local.LocalStorageResourceRefInventory");
			put("org.zstack.sdk.ManagementNodeInventory", "org.zstack.header.managementnode.ManagementNodeInventory");
			put("org.zstack.sdk.NetworkServiceL3NetworkRefInventory", "org.zstack.header.network.service.NetworkServiceL3NetworkRefInventory");
			put("org.zstack.sdk.NetworkServiceProviderInventory", "org.zstack.header.network.service.NetworkServiceProviderInventory");
			put("org.zstack.sdk.NotificationInventory", "org.zstack.core.notification.NotificationInventory");
			put("org.zstack.sdk.NotificationSubscriptionInventory", "org.zstack.core.notification.NotificationSubscriptionInventory");
			put("org.zstack.sdk.OssBucketInventory", "org.zstack.header.aliyun.oss.OssBucketInventory");
			put("org.zstack.sdk.OssBucketProperty", "org.zstack.header.aliyun.oss.OssBucketProperty");
			put("org.zstack.sdk.PolicyInventory", "org.zstack.header.identity.PolicyInventory");
			put("org.zstack.sdk.PolicyStatement", "org.zstack.header.identity.PolicyInventory$Statement");
			put("org.zstack.sdk.PolicyStatementEffect", "org.zstack.header.identity.AccountConstant$StatementEffect");
			put("org.zstack.sdk.PortForwardingRuleInventory", "org.zstack.network.service.portforwarding.PortForwardingRuleInventory");
			put("org.zstack.sdk.PriceInventory", "org.zstack.billing.PriceInventory");
			put("org.zstack.sdk.PrimaryStorageInventory", "org.zstack.header.storage.primary.PrimaryStorageInventory");
			put("org.zstack.sdk.QuotaInventory", "org.zstack.header.identity.QuotaInventory");
			put("org.zstack.sdk.QuotaUsage", "org.zstack.header.identity.Quota$QuotaUsage");
			put("org.zstack.sdk.ResourceInventory", "org.zstack.header.vo.ResourceInventory");
			put("org.zstack.sdk.RootVolumeSpending", "org.zstack.billing.RootVolumeSpending");
			put("org.zstack.sdk.RootVolumeSpendingInventory", "org.zstack.billing.RootVolumeSpendingInventory");
			put("org.zstack.sdk.SchedulerJobInventory", "org.zstack.header.core.scheduler.SchedulerJobInventory");
			put("org.zstack.sdk.SchedulerJobSchedulerTriggerInventory", "org.zstack.header.core.scheduler.SchedulerJobSchedulerTriggerInventory");
			put("org.zstack.sdk.SchedulerTriggerInventory", "org.zstack.header.core.scheduler.SchedulerTriggerInventory");
			put("org.zstack.sdk.SecurityGroupIngressRuleTO", "org.zstack.network.securitygroup.SecurityGroupIngressRuleTO");
			put("org.zstack.sdk.SecurityGroupInventory", "org.zstack.network.securitygroup.SecurityGroupInventory");
			put("org.zstack.sdk.SecurityGroupRuleInventory", "org.zstack.network.securitygroup.SecurityGroupRuleInventory");
			put("org.zstack.sdk.SessionInventory", "org.zstack.header.identity.SessionInventory");
			put("org.zstack.sdk.SftpBackupStorageInventory", "org.zstack.storage.backup.sftp.SftpBackupStorageInventory");
			put("org.zstack.sdk.ShareableVolumeVmInstanceRefInventory", "org.zstack.mevoco.ShareableVolumeVmInstanceRefInventory");
			put("org.zstack.sdk.SharedResourceInventory", "org.zstack.header.identity.SharedResourceInventory");
			put("org.zstack.sdk.ShellResult", "org.zstack.kvm.APIKvmRunShellEvent$ShellResult");
			put("org.zstack.sdk.SimulatorHostInventory", "org.zstack.header.simulator.SimulatorHostInventory");
			put("org.zstack.sdk.SnapShotSpendingInventory", "org.zstack.billing.SnapShotSpendingInventory");
			put("org.zstack.sdk.SnapshotLeafInventory", "org.zstack.header.storage.snapshot.VolumeSnapshotTree$SnapshotLeafInventory");
			put("org.zstack.sdk.SnapshotSpending", "org.zstack.billing.SnapshotSpending");
			put("org.zstack.sdk.Spending", "org.zstack.billing.Spending");
			put("org.zstack.sdk.SpendingDetails", "org.zstack.billing.SpendingDetails");
			put("org.zstack.sdk.SystemTagInventory", "org.zstack.header.tag.SystemTagInventory");
			put("org.zstack.sdk.TagInventory", "org.zstack.header.tag.TagInventory");
			put("org.zstack.sdk.TaskProgressInventory", "org.zstack.header.core.progress.TaskProgressInventory");
			put("org.zstack.sdk.UserGroupInventory", "org.zstack.header.identity.UserGroupInventory");
			put("org.zstack.sdk.UserInventory", "org.zstack.header.identity.UserInventory");
			put("org.zstack.sdk.UserTagInventory", "org.zstack.header.tag.UserTagInventory");
			put("org.zstack.sdk.VCenterBackupStorageInventory", "org.zstack.vmware.VCenterBackupStorageInventory");
			put("org.zstack.sdk.VCenterClusterInventory", "org.zstack.vmware.VCenterClusterInventory");
			put("org.zstack.sdk.VCenterDatacenterInventory", "org.zstack.vmware.VCenterDatacenterInventory");
			put("org.zstack.sdk.VCenterInventory", "org.zstack.vmware.VCenterInventory");
			put("org.zstack.sdk.VCenterPrimaryStorageInventory", "org.zstack.vmware.VCenterPrimaryStorageInventory");
			put("org.zstack.sdk.VRouterRouteEntryAO", "org.zstack.vrouterRoute.VRouterRouteEntryAO");
			put("org.zstack.sdk.VRouterRouteEntryInventory", "org.zstack.vrouterRoute.VRouterRouteEntryInventory");
			put("org.zstack.sdk.VRouterRouteTableInventory", "org.zstack.vrouterRoute.VRouterRouteTableInventory");
			put("org.zstack.sdk.VRouterType", "org.zstack.header.aliyun.network.vrouter.VRouterType");
			put("org.zstack.sdk.VipInventory", "org.zstack.network.service.vip.VipInventory");
			put("org.zstack.sdk.VirtualBorderRouterInventory", "org.zstack.header.aliyun.network.connection.VirtualBorderRouterInventory");
			put("org.zstack.sdk.VirtualRouterInterfaceInventory", "org.zstack.header.aliyun.network.connection.VirtualRouterInterfaceInventory");
			put("org.zstack.sdk.VirtualRouterOfferingInventory", "org.zstack.network.service.virtualrouter.VirtualRouterOfferingInventory");
			put("org.zstack.sdk.VirtualRouterVRouterRouteTableRefInventory", "org.zstack.vrouterRoute.VirtualRouterVRouterRouteTableRefInventory");
			put("org.zstack.sdk.VirtualRouterVmInventory", "org.zstack.network.service.virtualrouter.VirtualRouterVmInventory");
			put("org.zstack.sdk.VmInstanceInventory", "org.zstack.header.vm.VmInstanceInventory");
			put("org.zstack.sdk.VmNicInventory", "org.zstack.header.vm.VmNicInventory");
			put("org.zstack.sdk.VmNicSecurityGroupRefInventory", "org.zstack.network.securitygroup.VmNicSecurityGroupRefInventory");
			put("org.zstack.sdk.VmSpending", "org.zstack.billing.VmSpending");
			put("org.zstack.sdk.VmSpendingDetails", "org.zstack.billing.VmSpendingDetails");
			put("org.zstack.sdk.VniRangeInventory", "org.zstack.network.l2.vxlan.vxlanNetworkPool.VniRangeInventory");
			put("org.zstack.sdk.VolumeFormatReplyStruct", "org.zstack.header.volume.APIGetVolumeFormatReply$VolumeFormatReplyStruct");
			put("org.zstack.sdk.VolumeInventory", "org.zstack.header.volume.VolumeInventory");
			put("org.zstack.sdk.VolumeSnapshotBackupStorageRefInventory", "org.zstack.header.storage.snapshot.VolumeSnapshotBackupStorageRefInventory");
			put("org.zstack.sdk.VolumeSnapshotInventory", "org.zstack.header.storage.snapshot.VolumeSnapshotInventory");
			put("org.zstack.sdk.VolumeSnapshotTreeInventory", "org.zstack.header.storage.snapshot.VolumeSnapshotTreeInventory");
			put("org.zstack.sdk.VpcUserVpnGatewayInventory", "org.zstack.header.hybrid.network.vpn.VpcUserVpnGatewayInventory");
			put("org.zstack.sdk.VpcVirtualRouteEntryInventory", "org.zstack.header.aliyun.network.vrouter.VpcVirtualRouteEntryInventory");
			put("org.zstack.sdk.VpcVirtualRouterInventory", "org.zstack.header.aliyun.network.vrouter.VpcVirtualRouterInventory");
			put("org.zstack.sdk.VpcVpnConnectionInventory", "org.zstack.header.hybrid.network.vpn.VpcVpnConnectionInventory");
			put("org.zstack.sdk.VpcVpnGatewayInventory", "org.zstack.header.hybrid.network.vpn.VpcVpnGatewayInventory");
			put("org.zstack.sdk.VpcVpnIkeConfigInventory", "org.zstack.header.hybrid.network.vpn.VpcVpnIkeConfigInventory");
			put("org.zstack.sdk.VpcVpnIkeConfigStruct", "org.zstack.header.hybrid.network.vpn.VpcVpnIkeConfigStruct");
			put("org.zstack.sdk.VpcVpnIpSecConfigInventory", "org.zstack.header.hybrid.network.vpn.VpcVpnIpSecConfigInventory");
			put("org.zstack.sdk.VpcVpnIpSecConfigStruct", "org.zstack.header.hybrid.network.vpn.VpcVpnIpSecConfigStruct");
			put("org.zstack.sdk.VtepInventory", "org.zstack.network.l2.vxlan.vtep.VtepInventory");
			put("org.zstack.sdk.WebhookInventory", "org.zstack.header.core.webhooks.WebhookInventory");
			put("org.zstack.sdk.ZoneInventory", "org.zstack.header.zone.ZoneInventory");
        }
    };
}
